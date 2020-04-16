package com.practice.projectmanagerspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.projectmanagerspring.model.Backlog;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {
	
	Backlog findByProjectIdentifier(String projectIdentifier);
	

}
