package com.practice.projectmanagerspring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.projectmanagerspring.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	

}
