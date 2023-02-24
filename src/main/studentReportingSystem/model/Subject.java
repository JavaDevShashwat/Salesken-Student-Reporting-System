package com.salesken.studentReportingSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(indexName = "subjects")
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
	
	@Id
	private String subId;
	
	@Field(type = FieldType.Integer, name = "marks1")
	private Integer semester;
	
	@Field(type = FieldType.Text, name = "subect1")
	private String subject1;
	
	@Field(type = FieldType.Integer, name = "marks1")
	private Integer marks1;
	
	@Field(type = FieldType.Text, name = "subect1")
	private String subject2;
	
	@Field(type = FieldType.Integer, name = "marks1")
	private Integer marks2;
	
	@Field(type = FieldType.Text, name = "subect1")
	private String subject3;
	
	@Field(type = FieldType.Integer, name = "marks1")
	private Integer marks3;

}
