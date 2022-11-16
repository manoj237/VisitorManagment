package com.ezio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Attendance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id; 
	private Long userId;
	private String employeeName;
	private String email;
	private String date;
	private String checkIn;
	private String checkOut;
}
