package com.salesken.studentReportingSystem.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.salesken.studentReportingSystem.model.Subject;

public interface SubjectRepository extends ElasticsearchRepository<Subject, String>{

}
