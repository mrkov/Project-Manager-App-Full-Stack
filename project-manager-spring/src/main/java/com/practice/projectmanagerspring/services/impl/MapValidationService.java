package com.practice.projectmanagerspring.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MapValidationService {

	public ResponseEntity<?> isThereBadFieldValidation(BindingResult result) {

		if (result.hasErrors()) {

			Map<String, String> errorMap = new HashMap<>();
			result.getFieldErrors().stream()
					.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}

		return null;
	}
}
