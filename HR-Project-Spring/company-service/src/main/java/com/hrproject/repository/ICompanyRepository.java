package com.hrproject.repository;

import com.hrproject.repository.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICompanyRepository extends MongoRepository<Company, String> {

  boolean existsByCompanyTaxNumber(String companyTaxNumber);

  Optional<Company> findCompanyByCompanyManagerId(Long id);

  @Query("{ 'status' : 'PENDING' }")
  List<Company> findCompaniesWithPendingStatus();

  @Query("{ 'status' : 'ACTIVE' }")
  List<Company> findCompaniesWithActiveStatus();
}
