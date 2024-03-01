package com.hrproject.mapper;

import com.hrproject.dto.request.EmployeePaymentRequestDto;
import com.hrproject.dto.request.PaymentRequestDto;
import com.hrproject.dto.response.EmployeePaymentResponseDto;
import com.hrproject.dto.response.PaymentResponseDto;
import com.hrproject.repository.entity.Payment;
import com.hrproject.utility.CurrencyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPaymentMapper {
  IPaymentMapper INSTANCE = Mappers.getMapper(IPaymentMapper.class);

  Payment toPayment(PaymentRequestDto dto);

  PaymentResponseDto toPaymentResponseDto(Payment payment);

  List<PaymentResponseDto> toPaymentResponseDtoList(List<Payment> payments);

  Payment toPayment(EmployeePaymentRequestDto dto);

  EmployeePaymentResponseDto toEmployeePaymentResponseDto(Payment payment);

  List<EmployeePaymentResponseDto> toEmployeePaymentResponseDtoList(List<Payment> payments);


}
