package com.hrproject.repository;

import com.hrproject.repository.entity.Permission;
import com.hrproject.repository.entity.UserPermission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserPermissionRepository extends MongoRepository<UserPermission, String> {
  UserPermission findUserPermissionByUserId(String id);


}
