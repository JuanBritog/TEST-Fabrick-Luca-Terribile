package com.fabrick.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LogsRegistre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="logs_id")
	private long logsId;
	
	@Column(name="operation")
	private String operation;
	
	@Column(name="description")
	private String description;


}