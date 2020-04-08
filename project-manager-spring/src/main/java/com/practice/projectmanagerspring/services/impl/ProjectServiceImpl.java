package com.practice.projectmanagerspring.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
			if(project.getProjectIdentifier() != null)
				throw new ProjectIdException("There is already a project with id: " + project.getProjectIdentifier().toUpperCase());
			else {
				throw new ProjectIdException("Project identifier must not be null!!!");
			}
		}		
	}

	@Override
	public void delete(String projectIdentifier) {
		Optional<Project> found = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		
		if(!found.isPresent()) {
			throw new ProjectIdException("Can't delete project. There is no project with id: " + projectIdentifier.toUpperCase());
		}
		
		projectRepository.delete(found.get());
		
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

	@Override
	public Page<Project> findAllPage(int pageNum) {
		return projectRepository.findAll(PageRequest.of(pageNum, 4));
	}

}
