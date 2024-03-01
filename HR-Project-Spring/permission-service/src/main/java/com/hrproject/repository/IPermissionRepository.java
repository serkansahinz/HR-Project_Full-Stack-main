package com.hrproject.repository;

import com.hrproject.repository.entity.Permission;
import com.hrproject.repository.enums.EStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPermissionRepository extends MongoRepository<Permission, String> {

  //with @Query find pending permissions by company id. only with company id.status not a parameter

  @Query("{'companyId': ?0, 'status': 'PENDING'}")
  List<Permission> findPendingPermissionsByCompanyId(String companyId);

  List<Permission> findPermissionsByUserId(String userId);
}
