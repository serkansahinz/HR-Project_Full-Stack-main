package com.hrproject.service;

import com.hrproject.dto.request.EmployeePaymentRequestDto;
import com.hrproject.dto.request.PaymentRequestDto;
import com.hrproject.dto.response.EmployeePaymentResponseDto;
import com.hrproject.dto.response.PaymentInfoResponseDto;
import com.hrproject.dto.response.PaymentResponseDto;
import com.hrproject.manager.ICurrencyManager;
import com.hrproject.manager.ISalaryManager;
import com.hrproject.mapper.IPaymentMapper;
import com.hrproject.repository.IPaymentRepository;
import com.hrproject.repository.entity.Payment;
import com.hrproject.repository.enums.ECurrency;
import com.hrproject.repository.enums.EStatus;
import com.hrproject.repository.enums.EType;
import com.hrproject.utility.CurrencyInfo;

import com.hrproject.utility.FileService;
import com.hrproject.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentService extends ServiceManager<Payment, String> {
  private final IPaymentRepository paymentRepository;

  private final ISalaryManager salaryManager;
  private final ICurrencyManager currencyManager;
  private final FileService fileService;

  public PaymentService(IPaymentRepository paymentRepository,ISalaryManager salaryManager,ICurrencyManager currencyManager,FileService fileService) {
	super(paymentRepository);
	this.paymentRepository = paymentRepository;

	this.salaryManager = salaryManager;
	this.currencyManager = currencyManager;

	this.fileService = fileService;
  }

  public PaymentResponseDto createNewPayment(String id,PaymentRequestDto dto) {
	Payment payment = IPaymentMapper.INSTANCE.toPayment(dto);
	payment.setCompanyId(id);
	payment.setPaymentType(EType.valueOf(dto.getPaymentType().toUpperCase()));
	payment.setCurrency(ECurrency.valueOf(dto.getCurrency().toUpperCase()));
	payment.setStatus(EStatus.APPROVED);
	save(payment);
	return IPaymentMapper.INSTANCE.toPaymentResponseDto(payment);
  }

  public List<PaymentResponseDto> getAllIncome(String companyId) {
	List<Payment> payments = paymentRepository.findAllIncome(companyId);
	return IPaymentMapper.INSTANCE.toPaymentResponseDtoList(payments);
  }

  public List<PaymentResponseDto> getAllExpense(String companyId) {

	Payment payment = paymentRepository.findPaymentByCompanyIdAndPaymentDateAfter(LocalDate.now(),companyId);
	double totalSalary = salaryManager.totalSalary(companyId);

	if (payment == null) {
	  if (LocalDate.now().getDayOfMonth() > 15) {
		Payment payment1 = Payment.builder()
								  .paymentDate((LocalDate.now().plusMonths(1)).withDayOfMonth(15))//burası bir sonraki aya ayarlanacak öğren
								  .paymentDetail("Maaş Ödemesi")
								  .amount(totalSalary)
								  .paymentType(EType.MAAŞ)
								  .companyId(companyId)
								  .currency(ECurrency.TL)
								  .status(EStatus.APPROVED)
								  .paymentName("Maaş").build();
		save(payment1);
	  } else {
		Payment payment1 = Payment.builder()
								  .paymentDate((LocalDate.now()).withDayOfMonth(15))//burası bir sonraki aya ayarlanacak öğren
								  .paymentDetail("Maaş Ödemesi")
								  .amount(totalSalary)
								  .paymentType(EType.MAAŞ)
								  .companyId(companyId)
								  .currency(ECurrency.TL)
								  .status(EStatus.APPROVED)
								  .paymentName("Maaş").build();
		save(payment1);
	  }
	} else {
	  if (payment.getAmount() != totalSalary) {
		payment.setAmount(totalSalary);
		update(payment);
	  }
	}

	List<Payment> payments = paymentRepository.findAllExpense(companyId);
	return IPaymentMapper.INSTANCE.toPaymentResponseDtoList(payments);
  }

  public List<CurrencyInfo> getCurrency() {
	ResponseEntity<Object> responseEntity = currencyManager.getCurrency();
	Object responseBody = responseEntity.getBody();

	if (responseBody instanceof Map) {
	  Map<String, Object> responseMap = (Map<String, Object>) responseBody;
	  Object tcmbData = responseMap.get("TCMB_AnlikKurBilgileri");
	  if (tcmbData instanceof List) {

		List<Map<String, Object>> tcmbList = (List<Map<String, Object>>) tcmbData;
		return tcmbList.stream()
					   .map(this::mapToCurrencyInfo)
					   .collect(Collectors.toList());
	  }
	}
	return null;
  }

  private CurrencyInfo mapToCurrencyInfo(Map<String, Object> currencyMap) {
	CurrencyInfo currencyInfo = new CurrencyInfo();
	currencyInfo.setIsim((String) currencyMap.get("Isim"));
	currencyInfo.setCurrencyName((String) currencyMap.get("CurrencyName"));
	currencyInfo.setForexBuying(currencyMap.get("ForexBuying").toString());
	currencyInfo.setForexSelling(currencyMap.get("ForexSelling").toString());
	currencyInfo.setBanknoteBuying(currencyMap.get("BanknoteBuying").toString());
	currencyInfo.setBanknoteSelling(currencyMap.get("BanknoteSelling").toString());
	currencyInfo.setCrossRateUSD(currencyMap.get("CrossRateUSD").toString());
	currencyInfo.setCrossRateOther(currencyMap.get("CrossRateOther").toString());

	return currencyInfo;
  }

  public double calculateTotalIncome(String companyId) {
	List<Payment> payments = paymentRepository.findAllIncome(companyId);
	List<CurrencyInfo> currency = getCurrency();
	double sum = 0;
	for (Payment payment : payments) {
	  if (payment.getCurrency() != null) { // Null kontrolü ekleniyor
		if (payment.getCurrency().equals(ECurrency.USD)) {
		  sum += payment.getAmount() * Double.parseDouble(currency.get(0).getForexBuying());
		} else if (payment.getCurrency().equals(ECurrency.EURO)) {
		  sum += payment.getAmount() * Double.parseDouble(currency.get(3).getForexBuying());
		} else {
		  sum += payment.getAmount();
		}
	  }
	}
	return sum;
  }

  public double calculateTotalExpense(String companyId) {
	List<Payment> payments = paymentRepository.findAllExpense(companyId);
	List<CurrencyInfo> currency = getCurrency();
	double sum = 0;
	for (Payment payment : payments) {
	  if (payment.getCurrency() != null) { // Null kontrolü ekleniyor
		if (payment.getCurrency().equals(ECurrency.USD)) {
		  sum += payment.getAmount() * Double.parseDouble(currency.get(0).getForexBuying());
		} else if (payment.getCurrency().equals(ECurrency.EURO)) {
		  sum += payment.getAmount() * Double.parseDouble(currency.get(3).getForexBuying());
		} else {
		  sum += payment.getAmount();
		}
	  }
	}
	return sum;
  }

  public PaymentInfoResponseDto calculateTotalIncomeAndExpense(String companyId) {

	return PaymentInfoResponseDto.builder()
								 .totalIncome(calculateTotalIncome(companyId))
								 .totalExpense(calculateTotalExpense(companyId))
								 .netValue(calculateTotalIncome(companyId) - calculateTotalExpense(companyId))
								 .build();
  }

  public void deleteById(String id) {
	Payment payment = findById(id).orElseThrow(() -> new IllegalArgumentException("Ödeme bulunamadı"));
	delete(payment);
  }

  public EmployeePaymentResponseDto employeeCreatePayment(String companyId,String userId,EmployeePaymentRequestDto dto) {
	Payment payment = IPaymentMapper.INSTANCE.toPayment(dto);
	payment.setCompanyId(companyId);
	payment.setUserId(userId);
	payment.setCurrency(ECurrency.valueOf(dto.getCurrency().toUpperCase()));
	payment.setFullName(dto.getName() + " " + dto.getSurname());
	payment.setPaymentType(EType.GİDER);
	payment.setStatus(EStatus.PENDING);
	save(payment);
	return IPaymentMapper.INSTANCE.toEmployeePaymentResponseDto(payment);
  }

  public List<EmployeePaymentResponseDto> getAllPendingPayment(String companyId) {
	List<Payment> payments = paymentRepository.findAllPendingPayment(companyId);
	List<EmployeePaymentResponseDto> dto = IPaymentMapper.INSTANCE.toEmployeePaymentResponseDtoList(payments);
	for (int i = 0; i < dto.size(); i++) {
	  dto.get(i).setUpdatedDate(convertMillisToLocalDate(payments.get(i).getUpdateDate()));
	  dto.get(i).setCreatedDate(convertMillisToLocalDate(payments.get(i).getCreateDate()));
	}
	return dto;
  }

  public List<EmployeePaymentResponseDto> getAllPaymentsByUserId(String userId) {
	List<Payment> payments = paymentRepository.findAllByUserId(userId);
	List<EmployeePaymentResponseDto> dto = IPaymentMapper.INSTANCE.toEmployeePaymentResponseDtoList(payments);
	for (int i = 0; i < dto.size(); i++) {
	  dto.get(i).setUpdatedDate(convertMillisToLocalDate(payments.get(i).getUpdateDate()));
	  dto.get(i).setCreatedDate(convertMillisToLocalDate(payments.get(i).getCreateDate()));
	}
	return dto;
  }

  public static LocalDate convertMillisToLocalDate(long millis) {
	Instant instant = Instant.ofEpochMilli(millis);
	return instant.atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public EmployeePaymentResponseDto activateStatus(String id) {
	Payment payment = findById(id).orElseThrow(() -> new IllegalArgumentException("Ödeme bulunamadı"));
	payment.setStatus(EStatus.APPROVED);
	update(payment);
	return IPaymentMapper.INSTANCE.toEmployeePaymentResponseDto(payment);
  }

  public boolean deleteEmployeePayment(String paymentId) {
	Payment payment = findById(paymentId).orElseThrow(() -> new IllegalArgumentException("Ödeme bulunamadı"));
	payment.setStatus(EStatus.REJECTED);
	update(payment);
	return true;
  }

  public EmployeePaymentResponseDto uploadFile(String id,MultipartFile file) {
	Payment payment = findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
	String fileUrl = fileService.uploadFile(file);
	payment.setFileUrl(fileUrl);
	update(payment);
	return IPaymentMapper.INSTANCE.toEmployeePaymentResponseDto(payment);
  }
}
