package ch.bzz.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
	private String id;
	private String accountNumber;
	private String name;
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Project project;
}
