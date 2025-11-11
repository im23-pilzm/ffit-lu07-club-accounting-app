package ch.bzz.controller;

import ch.bzz.generated.model.LoginProject200Response;
import ch.bzz.generated.model.LoginRequest;
import ch.bzz.models.Project;
import ch.bzz.repository.ProjectRepository;
import ch.bzz.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProjectApiController implements ch.bzz.generated.api.ProjectApi {

    private final ProjectRepository projectRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    public ProjectApiController(ProjectRepository projectRepository, JwtUtil jwtUtil) {
        this.projectRepository = projectRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<Void> createProject(LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getProjectName() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String projectName = loginRequest.getProjectName();

        if (projectRepository.existsById(projectName)) {
            // project already exists -> consider this invalid input
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String hashed = encoder.encode(loginRequest.getPassword());
        Project project = new Project(projectName, hashed);
        projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<LoginProject200Response> loginProject(LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getProjectName() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String projectName = loginRequest.getProjectName();
        Optional<Project> opt = projectRepository.findById(projectName);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Project project = opt.get();
        if (!encoder.matches(loginRequest.getPassword(), project.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtUtil.generateToken(projectName);
        LoginProject200Response resp = new LoginProject200Response();
        resp.setAccessToken(token);

        return ResponseEntity.ok(resp);
    }
}
