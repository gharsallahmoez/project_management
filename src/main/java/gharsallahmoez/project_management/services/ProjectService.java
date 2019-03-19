package gharsallahmoez.project_management.services;

import gharsallahmoez.project_management.domain.Project;
import gharsallahmoez.project_management.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
     @Autowired
     private ProjectRepository projectRepository ;

     public Project saveOrUpdateProject(Project project){
         //Logic
         return projectRepository.save(project);
     }

}
