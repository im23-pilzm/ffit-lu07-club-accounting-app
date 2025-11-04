package ch.bzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.bzz.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

}
