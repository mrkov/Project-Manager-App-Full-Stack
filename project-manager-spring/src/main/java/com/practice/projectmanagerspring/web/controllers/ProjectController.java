package com.practice.projectmanagerspring.web.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.projectmanagerspring.exceptions.ProjectIdException;
import com.practice.projectmanagerspring.model.Project;
import com.practice.projectmanagerspring.services.ProjectService;
import com.practice.projectmanagerspring.services.impl.MapValidationService;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
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

	@GetMapping(path = "/{identifier}")
	public ResponseEntity<?> findByProjectIdentifier(@PathVariable String identifier) {
		Optional<Project> optional = projectService.findByProjectIdentifier(identifier);
		if (!optional.isPresent()) {
			throw new ProjectIdException("There is no project with id: " + identifier.toUpperCase());
		}
		Project foundProject = projectService.findByProjectIdentifier(identifier).get();
		return new ResponseEntity<Project>(foundProject, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0", required = false) int pageNum) {
		Page<Project> page = projectService.findAllPage(pageNum);

		if (page.isEmpty()) {
			return new ResponseEntity<String>("There are no projects.", HttpStatus.NOT_FOUND);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.valueOf(page.getTotalPages()).toString());

		return new ResponseEntity<List<Project>>(page.toList(), headers, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{identifier}")
	public String deleteMethodName(@PathVariable String identifier) {
		projectService.delete(identifier);
		return "Success";
	}

	@PutMapping(value = "/{identifier}")
	public ResponseEntity<?> updateProject(@PathVariable String identifier,@Valid @RequestBody Project project) {
		System.out.println("HAHAHAHAHAHAHAHAHAH");
		Optional<Project> found = projectService.findByProjectIdentifier(identifier);
		
		if (!found.isPresent()) {
			throw new ProjectIdException(
					"Can't update project. There is no project with id: " + identifier.toUpperCase());
		}
		if(project.getProjectIdentifier() == null || !found.get().getProjectIdentifier().equalsIgnoreCase(project.getProjectIdentifier())) {
			return new ResponseEntity<String>("It's not possible to change project identifier",HttpStatus.BAD_REQUEST);
		}
		System.out.println("HIHIHIHIHIH");
		Project updated = projectService.save(project);
		
		return new ResponseEntity<Project>(updated, HttpStatus.OK);
	}

}
