package gharsallahmoez.project_management.repositories;

import gharsallahmoez.project_management.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//this annotation not longuer necessary
@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();

}
