package com.practice.projectmanagerspring.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.projectmanagerspring.exceptions.ProjectIdException;
import com.practice.projectmanagerspring.model.Backlog;
import com.practice.projectmanagerspring.model.ProjectTask;
import com.practice.projectmanagerspring.repositories.BacklogRepository;
import com.practice.projectmanagerspring.repositories.ProjectTaskRepository;
import com.practice.projectmanagerspring.services.ProjectTaskService;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	@Override
	@Transactional
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

		if (backlog == null) {
			throw new ProjectIdException("Project with ID: " + projectIdentifier + " doesn't exist.");
		}
		
		Integer backlogSequence = backlog.getPTSequence();
		backlogSequence++;
		backlog.setPTSequence(backlogSequence);
		
		projectTask.setBacklog(backlog);
		projectTask.setProjectIdentifier(projectIdentifier);
		projectTask.setProjectSequence(projectIdentifier + "-" +backlogSequence);
		
		if(projectTask.getPriority() == 0 || projectTask.getPriority() == null)
			projectTask.setPriority(3);
		
		if(projectTask.getStatus() == "" || projectTask.getStatus() == null) {
			projectTask.setStatus("TO_DO");
		}
		
		
		return projectTaskRepository.save(projectTask);
	}

}
