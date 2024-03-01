package com.hrproject.manager;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "https://hasanadiguzel.com.tr/api", decode404 = true, name = "payment-currency")
public interface ICurrencyManager {

  @RequestMapping(value = "/kurgetir", method = RequestMethod.GET)
   ResponseEntity<Object> getCurrency();
}