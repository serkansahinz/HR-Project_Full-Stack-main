package com.hrproject.repository;

import com.hrproject.repository.entity.UserProfile;
import com.hrproject.repository.enums.EStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends MongoRepository<UserProfile, String> {

  Optional<UserProfile> findByAuthId(Long authId);

  Optional<UserProfile> findByEmail(String email);

  List<UserProfile> findByStatus(EStatus status);

  @Query("{ 'role': { $in: [ 'EMPLOYEE', 'COMPANY_MANAGER' ] }, 'companyId': ?0, 'status': { $ne: 'DELETED' } }")
  List<UserProfile> findEmployeesByCompanyId(String companyId);

  @Query("{authId: {$gt: ?0}}")
  Optional<UserProfile> findUserGtId(Long authId);

  @Query("{$or: [{authId:{$gt: ?0}},{status: ?1}]}")
  Optional<UserProfile> getUserGtIdAndStatus(Long authId,EStatus status);

  @Query("{status: 'ACTIVE'}")
  Optional<UserProfile> findActiveProfile();
}
