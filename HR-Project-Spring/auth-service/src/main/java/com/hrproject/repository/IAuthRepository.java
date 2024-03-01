package com.hrproject.repository;

import com.hrproject.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAuthRepository extends JpaRepository<Auth, Long> {

  Optional<Auth> findAuthByEmailAndPassword(String email,String password);

  Optional<Auth> findAuthByEmail(String email);

  boolean existsByEmail(String email);

  @Query("select a from Auth a where a.role='COMPANY_MANAGER' and a.status='PENDING'")
  List<Auth> findAllPendingCompanyManager();
}
