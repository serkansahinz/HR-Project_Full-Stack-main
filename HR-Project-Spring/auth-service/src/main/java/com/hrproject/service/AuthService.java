package com.hrproject.service;

import com.hrproject.dto.request.*;
import com.hrproject.dto.response.RegisterResponseDto;
import com.hrproject.exception.AuthManagerException;
import com.hrproject.exception.ErrorType;
import com.hrproject.mapper.IAuthMapper;
import com.hrproject.rabbitmq.model.*;
import com.hrproject.rabbitmq.producer.*;
import com.hrproject.repository.IAuthRepository;
import com.hrproject.repository.entity.Auth;
import com.hrproject.repository.enums.ERole;
import com.hrproject.repository.enums.EStatus;
import com.hrproject.utility.CodeGenerator;
import com.hrproject.utility.JwtTokenManager;
import com.hrproject.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

  private final IAuthRepository authRepository;
  private final JwtTokenManager jwtTokenManager;
  private final RegisterProducer registerProducer;
  private final MailRegisterProducer mailRegisterProducer;
  private final ActivationProducer activationProducer;
  private final CompanyRegisterProducer companyRegisterProducer;
  private final DeleteUserByIdProducer deleteUserByIdProducer;
  private final CreateEmployeeAuthProducer createEmployeeAuthProducer;
  private final SendEmployeeMailProducer sendEmployeeMailProducer;

  public AuthService(IAuthRepository authRepository,JwtTokenManager jwtTokenManager,RegisterProducer registerProducer,MailRegisterProducer mailRegisterProducer,ActivationProducer activationProducer,CompanyRegisterProducer companyRegisterProducer,DeleteUserByIdProducer deleteUserByIdProducer,CreateEmployeeAuthProducer createEmployeeAuthProducer,SendEmployeeMailProducer sendEmployeeMailProducer) {
	super(authRepository);
	this.authRepository = authRepository;
	this.jwtTokenManager = jwtTokenManager;
	this.registerProducer = registerProducer;
	this.mailRegisterProducer = mailRegisterProducer;
	this.activationProducer = activationProducer;
	this.companyRegisterProducer = companyRegisterProducer;
	this.deleteUserByIdProducer = deleteUserByIdProducer;
	this.createEmployeeAuthProducer = createEmployeeAuthProducer;
	this.sendEmployeeMailProducer = sendEmployeeMailProducer;
  }

  @Transactional
  public RegisterResponseDto guestRegister(RegisterRequestDto dto) {
	Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
	auth.setRole(ERole.GUEST);
	auth.setActivationCode(CodeGenerator.generateCode());
	if (authRepository.existsByEmail(dto.getEmail()) && auth.getStatus() != EStatus.DELETED) {
	  throw new AuthManagerException(ErrorType.EMAIL_ALREADY_EXIST);
	}
	save(auth);
	dto.setId(auth.getId());

	RegisterModel model = IAuthMapper.INSTANCE.toRegisterModel(dto);
	model.setRole("GUEST");
	model.setName(capitalizeFirstLetters(dto.getName()));
	model.setSurname(capitalizeFirstLetters(dto.getSurname()));
	registerProducer.sendNewUser(model);

	RegisterResponseDto responseDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
	String token = jwtTokenManager.createToken(auth.getId())
								  .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));
	responseDto.setToken(token);

	MailRegisterModel mailRegisterModel = IAuthMapper.INSTANCE.toMailRegisterModel(auth);
	mailRegisterModel.setToken(token);

	mailRegisterProducer.sendActivationCode(mailRegisterModel);

	return responseDto;
  }

  @Transactional
  public RegisterResponseDto managerRegister(ManagerRegisterDto dto) {
	Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
	auth.setRole(ERole.COMPANY_MANAGER);
	auth.setActivationCode(CodeGenerator.generateCode());
	if (authRepository.existsByEmail(dto.getEmail()) && auth.getStatus() != EStatus.DELETED) {
	  throw new AuthManagerException(ErrorType.EMAIL_ALREADY_EXIST);
	}
	save(auth);
	dto.setId(auth.getId());

	RegisterModel model1 = IAuthMapper.INSTANCE.toRegisterModel(dto);
	model1.setRole("COMPANY_MANAGER");
	model1.setName(capitalizeFirstLetters(dto.getName()));
	model1.setSurname(capitalizeFirstLetters(dto.getSurname()));
	model1.setCompanyName(capitalizeFirstLetters(dto.getCompanyName()));
	registerProducer.sendNewUser(model1);

	RegisterResponseDto responseDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
	String token = jwtTokenManager.createToken(auth.getId())
								  .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));

	responseDto.setToken(token);
	CompanyRegisterModel model = IAuthMapper.INSTANCE.toCompanyRegisterModel(dto);
	model.setToken(token);
	model.setCompanyEmail(dto.getCompanyEmail());
	model.setCompanyPhone(dto.getCompanyPhone());
	model.setCompanyName(capitalizeFirstLetters(dto.getCompanyName()));
	model.setRemainingDays(dto.getRemainingDays());

	companyRegisterProducer.sendCompany(model);

	return responseDto;
  }

  public String login(LoginRequestDto dto) {
	Optional<Auth> optionalAuth = authRepository.findAuthByEmailAndPassword(dto.getEmail(),dto.getPassword());
	if (optionalAuth.isEmpty()) {
	  throw new AuthManagerException(ErrorType.LOGIN_ERROR);
	}
	if (!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
	  throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
	}

	return jwtTokenManager.createToken(optionalAuth.get().getId(),optionalAuth.get().getRole())
						  .orElseThrow(() -> new AuthManagerException(ErrorType.TOKEN_NOT_CREATED));
  }

  public Boolean createEmployee(CreateEmployeeRequestDto dto) {

	Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
	auth.setRole(ERole.EMPLOYEE);
	auth.setStatus(EStatus.ACTIVE);

	String name = normalizeAndRemoveSpaces(dto.getName().toLowerCase());
	String surname = normalizeAndRemoveSpaces(dto.getSurname().toLowerCase());
	String companyName = normalizeAndRemoveSpaces(dto.getCompanyName().toLowerCase());

	String employeeEmail = name + "." + surname + "@" + companyName + ".com";
	String password = name + surname;

	auth.setEmail(employeeEmail);
	auth.setPassword(password);

	save(auth);

	CreateEmployeeAuthModel model = IAuthMapper.INSTANCE.toCreateEmployeeAuthModel(dto);
	model.setAuthId(auth.getId());
	model.setRole(ERole.EMPLOYEE);
	model.setStatus(EStatus.ACTIVE);
	model.setEmail(employeeEmail);
	model.setName(capitalizeFirstLetters(dto.getName()));
	model.setSurname(capitalizeFirstLetters(dto.getSurname()));
	model.setCompanyName(capitalizeFirstLetters(dto.getCompanyName()));

	createEmployeeAuthProducer.sendCreatedEmployeeToAuth(model);

	EmployeeMailModel mailModel = EmployeeMailModel.builder()
												   .personalEmail(dto.getPersonalEmail().toLowerCase(Locale.ENGLISH))
												   .email(employeeEmail)
												   .name(dto.getName())
												   .surname(dto.getSurname())
												   .password(password)
												   .build();

	sendEmployeeMailProducer.sendNewEmployeeMail(mailModel);

	return true;
  }

  private String normalizeAndRemoveSpaces(String input) {
	String normalizedString = Normalizer.normalize(input,Normalizer.Form.NFD);
	// Add the following lines to preserve the following characters
	normalizedString = normalizedString.replace("ı","i");
	normalizedString = normalizedString.replace("ö","o");
	normalizedString = normalizedString.replace("ü","u");
	normalizedString = normalizedString.replace("ç","c");
	normalizedString = normalizedString.replace("ş","s");
	normalizedString = normalizedString.replace("ğ","g");

	normalizedString = normalizedString.replaceAll("[^\\p{ASCII}]","");
	normalizedString = normalizedString.replaceAll("\\s+","");

	return normalizedString;
  }

  public String capitalizeFirstLetters(String ifade) {
	if (ifade == null || ifade.isEmpty()) {
	  return ifade; // Boş veya null ifade varsa aynısını geri döndür
	}

	StringBuilder sb = new StringBuilder();

	boolean harfBuyut = true; // İlk karakter ve boşluktan sonraki karakteri büyütme durumu için bayrak

	for (char c : ifade.toCharArray()) {
	  if (Character.isWhitespace(c)) {
		harfBuyut = true; // Boşluk durumunda bir sonraki karakter büyüyecek
		sb.append(c); // Boşluğu ekle
	  } else if (harfBuyut) {
		sb.append(Character.toUpperCase(c)); // Büyük harf ekle
		harfBuyut = false; // Bir sonraki büyük harf için bayrağı kapat
	  } else {
		sb.append(c); // Diğer durumlarda karakteri ekle
	  }
	}

	return sb.toString();
  }

  public Boolean activateStatus(String token) {
	Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
	if (authId.isPresent()) {
	  Optional<Auth> auth = findById(authId.get());
	  auth.get().setStatus(EStatus.ACTIVE);
	  update(auth.get());
	  activationProducer.activateStatus(token); // rabbitmq ile haberleşme
	  return true;
	}
	throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
  }

  public String deleteCompanyManagerById(Long id) {
	Auth auth = findById(id).orElseThrow(() -> new AuthManagerException(ErrorType.USER_NOT_FOUND));
	auth.setStatus(EStatus.DELETED);
	update(auth);
	return "silme başarılı";
  }

  public String deleteUserById(Long id) {
	Auth auth = findById(id).orElseThrow(() -> new AuthManagerException(ErrorType.USER_NOT_FOUND));
	auth.setStatus(EStatus.DELETED);
	update(auth);
	deleteUserByIdProducer.deleteUserById(id);
	return "silme başarılı";
  }
}
