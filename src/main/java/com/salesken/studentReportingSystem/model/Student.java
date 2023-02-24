package com.salesken.studentReportingSystem.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(indexName = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	private Integer rollNo;
	
	@Field(type = FieldType.Text, name = "name")
	private String name;
	
	@JsonIgnore
	private List<Subject> subjects = new ArrayList<>();
	
}
