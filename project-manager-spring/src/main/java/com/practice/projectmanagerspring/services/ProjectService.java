package com.practice.projectmanagerspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.practice.projectmanagerspring.model.Project;

public interface ProjectService {
	
	public Project save(Project project);
	public void delete(String projectIdentifier);
	public List<Project> findAll();
	public Optional<Project> findOne(Long id);
	public Optional<Project> findByProjectIdentifier(String projectIdentifier);
	public Page<Project> findAllPage(int pageNum);
	

}
