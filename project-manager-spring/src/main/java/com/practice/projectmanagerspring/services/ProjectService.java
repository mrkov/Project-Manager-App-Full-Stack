package com.practice.projectmanagerspring.services;

import java.util.List;
import java.util.Optional;

import com.practice.projectmanagerspring.model.Project;

public interface ProjectService {
	
	public Project save(Project project);
	public void delete(Long id);
	public List<Project> findAll();
	public Optional<Project> findOne(Long id);
	public Optional<Project> findByProjectIdentifier(String projectIdentifier);
	

}
