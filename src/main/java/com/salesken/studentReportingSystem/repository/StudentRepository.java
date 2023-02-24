package com.salesken.studentReportingSystem.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.salesken.studentReportingSystem.model.Student;

public interface StudentRepository extends ElasticsearchRepository<Student, Integer>{


}
