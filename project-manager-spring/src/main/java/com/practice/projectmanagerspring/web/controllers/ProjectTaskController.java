package com.practice.projectmanagerspring.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.projectmanagerspring.model.ProjectTask;
import com.practice.projectmanagerspring.services.ProjectTaskService;
import com.practice.projectmanagerspring.services.impl.MapValidationService;

@RestController
@RequestMapping(path = "/api/projects/{projectId}")
@CrossOrigin
public class ProjectTaskController {

	@Autowired
	ProjectTaskService projectTaskService;

	@Autowired
	MapValidationService mapValidationService;

	@PostMapping(value = "/")
	public ResponseEntity<?> saveProjectTask(@Valid @RequestBody ProjectTask projectTask,
			@PathVariable(name = "projectId") String projectId, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationService.isThereBadFieldValidation(result);
		if (errorMap != null)
			return errorMap;
		
		ProjectTask saved = projectTaskService.addProjectTask(projectId, projectTask);

		return new ResponseEntity<ProjectTask>(saved, HttpStatus.CREATED);
	}

}
