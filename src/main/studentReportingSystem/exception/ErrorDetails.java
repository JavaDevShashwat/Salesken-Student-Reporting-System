package com.salesken.studentReportingSystem.exception;

import java.time.LocalDateTime;

import org.apache.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private HttpStatus httpStatus;
	private String details;
}
