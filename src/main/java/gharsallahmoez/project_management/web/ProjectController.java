package gharsallahmoez.project_management.web;
import gharsallahmoez.project_management.domain.Project;
import gharsallahmoez.project_management.repositories.ProjectRepository;
import gharsallahmoez.project_management.services.MapValidationErrorService;
import gharsallahmoez.project_management.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project , BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return  errorMap;
        //but if the user enter the same id ! errorMap will accept but database no ! so we have to create 3 exception classes
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project with Id : '"+projectId+"' was deleted",HttpStatus.OK);
    }
}