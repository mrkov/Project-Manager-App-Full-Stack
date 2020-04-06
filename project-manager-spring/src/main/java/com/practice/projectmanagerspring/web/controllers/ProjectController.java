package com.practice.projectmanagerspring.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.projectmanagerspring.model.Project;
import com.practice.projectmanagerspring.services.ProjectService;
import com.practice.projectmanagerspring.services.impl.MapValidationService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MapValidationService mapValidationService;

	@PostMapping
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationService.isThereBadFieldValidation(result);
		if (errorMap != null)
			return errorMap;

		Project savedProject = projectService.save(project);

		return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
	}

}
