package com.hrproject.repository;

import com.hrproject.repository.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICommentRepository extends MongoRepository<Comment, String> {

  //List of comment status = PENDING and company id = ? with @Query
  @Query("{'status': 'PENDING'}")
  List<Comment> findAllPendingCommentsByCompanyId();

  //List of activate comments of an company with @Query
  @Query("{'status': 'ACTIVE', 'companyId': ?0}")
  List<Comment> findAllActiveCommentsByCompanyId(String companyId);

  //List of active comments of an user with @Query

  @Query("{'status': 'ACTIVE', 'userId': ?0}")
  List<Comment> findAllActiveCommentsByUserId(String userId);

  //list of pending comments of an user with @Query

  @Query("{'status': 'PENDING', 'userId': ?0}")
  List<Comment> findAllPendingCommentsByUserId(String userId);

  //List of all comments by user ıd with @Query

  List<Comment> findAllByUserId(String userId);
}
