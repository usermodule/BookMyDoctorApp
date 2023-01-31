package com.nv.doctorapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityDates {

	private LocalDate fromDate;
	private LocalDate toDate;

}
