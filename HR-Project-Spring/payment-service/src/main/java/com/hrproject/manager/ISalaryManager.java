package com.hrproject.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${feign.user}", decode404 = true, name = "payment-user")
public interface ISalaryManager {

  @GetMapping("/total_salary/{companyId}")
  double totalSalary(@PathVariable String companyId);
}
