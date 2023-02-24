package com.salesken.studentReportingSystem.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.salesken.studentReportingSystem.model.Subject;

@Repository
public interface SubjectRepository extends ElasticsearchRepository<Subject, String>{

}
