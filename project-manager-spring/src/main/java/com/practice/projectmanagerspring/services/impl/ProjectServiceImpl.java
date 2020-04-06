package com.practice.projectmanagerspring.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.projectmanagerspring.exceptions.ProjectIdException;
import com.practice.projectmanagerspring.model.Project;
import com.practice.projectmanagerspring.repositories.ProjectRepository;
import com.practice.projectmanagerspring.services.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public Project save(Project project) {		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " already exists.");
		}		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Project> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Project> findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Project> findByProjectIdentifier(String projectIdentifier) {
		// TODO Auto-generated method stub
		return projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
	}

}
