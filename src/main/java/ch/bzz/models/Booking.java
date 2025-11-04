package ch.bzz.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
	private String id;
	private String bookingNumber;
	private LocalDate date;
	private String text;
	private Account debitAccount;
	private Account creditAccount;
	private BigDecimal amount;
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Project project;
}
