package com.hrproject.repository;

import com.hrproject.dto.response.AdvanceResponseDto;
import com.hrproject.repository.entity.Advance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdvanceRepository extends MongoRepository<Advance, String> {

  @Query(value = "{companyId:?0,status:'PENDING'}")
  List<Advance> findPendingAdvancesByCompanyId(String companyId);

  List<Advance> findAllByUserId(String userId);
}
