package com.onlie.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.onlie.shop.service.ShopService;

@RestController
public class ShopApiController {
	
	@Autowired
	ShopService shopService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/check-email/{email}")
	public ResponseEntity checkEmail(@PathVariable String email) {
		if(this.shopService.isEmailSatisfy(email.toLowerCase())) {			
			return ResponseEntity.ok("Email is satisfied");
		}else {
			return new ResponseEntity("Email is already exist",HttpStatus.CONFLICT);
		}
	}

}
