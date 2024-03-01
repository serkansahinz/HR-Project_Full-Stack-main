package com.hrproject.repository;

import com.hrproject.repository.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IPaymentRepository extends MongoRepository<Payment, String> {

  //payment type income olanları getir company idye göre
  @Query("{'paymentType':'GELİR','status':'APPROVED','companyId':?0}")
  List<Payment> findAllIncome(String companyId);

  //payment type GİDER ve MAAŞ olanları getir
  @Query("{'paymentType':{$in:['GİDER','MAAŞ']},'companyId':?0}")
  List<Payment> findAllExpense(String companyId);

  //with @Query find all pending payments

  @Query("{'status':'PENDING','companyId':?0}")
  List<Payment> findAllPendingPayment(String companyId);

  List<Payment> findAllByUserId(String userId);

  //with @Query find payments by company id which is payment type is SALARY and payment date today or after today but paymentDate is not a parameter

  @Query("{'paymentType':'MAAŞ','paymentDate':{$gte:?0},'companyId':?1}")
  Payment findPaymentByCompanyIdAndPaymentDateAfter(LocalDate paymentDate,String companyId);
}
