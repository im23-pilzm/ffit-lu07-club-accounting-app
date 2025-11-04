package ch.bzz.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "project")
public class Project {
	@Id
	@Column(name = "project_name", nullable = false, unique = true, length = 100)
	private String projectName;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
}


