package com.hrproject.utility;

import com.hrproject.repository.IAuthRepository;
import com.hrproject.repository.entity.Auth;
import com.hrproject.repository.enums.ERole;
import com.hrproject.repository.enums.EStatus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component

public class AdminUserInitializer extends ServiceManager<Auth, Long> implements CommandLineRunner {

  private final IAuthRepository authRepository;

  public AdminUserInitializer(JpaRepository<Auth, Long> repository,IAuthRepository authRepository) {
	super(repository);
	this.authRepository = authRepository;
  }

  @Override
  public void run(String... args) throws Exception {
	Auth adminUser = new Auth();
	adminUser.setEmail("admin@hrproject.com");
	adminUser.setPassword("root");
	adminUser.setStatus(EStatus.ACTIVE);
	adminUser.setRole(ERole.ADMIN);

	if (authRepository.findAuthByEmail(adminUser.getEmail()).isEmpty()) {
	  save(adminUser);
	}
  }
}
