package com.salesken.studentReportingSystem.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salesken.studentReportingSystem.exception.StudentException;
import com.salesken.studentReportingSystem.model.Student;
import com.salesken.studentReportingSystem.model.Subject;
import com.salesken.studentReportingSystem.repository.StudentRepository;

import org.springframework.http.HttpStatus;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/addStudents")
	public ResponseEntity<Student>  addStudent(@RequestBody Student student) {
		
		Student added = studentRepository.save(student);
		
		return new ResponseEntity<Student>(added, HttpStatus.OK);
		
	}
	
	@GetMapping("/average/{sem}")
	public ResponseEntity<Double> getAverageOfClass(@PathVariable("sem")Integer semester) throws StudentException{
				
		List<Student> students = (List<Student>) studentRepository.findAll();
		int totalMarks = 0;
		int totalSubjects = 0;
		
		if(students.size()<0) {
			throw new StudentException("No student is added in system yet.");
		}
		for (Student student : students) {
			for (Subject subject : student.getSubjects()) {
				if (subject.getSemester().equals(semester)) {
					totalMarks += subject.getMarks1() + subject.getMarks2() + subject.getMarks3();
					totalSubjects += 3;
				}
			}
		}
		double result = (double) totalMarks / totalSubjects;
		
		return new ResponseEntity<Double>(result, HttpStatus.OK);
	}

	@GetMapping("/average/{sem}")
	public ResponseEntity<String> getAverageOfSubjects(@PathVariable("sem")Integer semester) throws StudentException{
		
		List<Student> students = (List<Student>) studentRepository.findAll();
		int marks1 = 0;
		int marks2 = 0;
		int marks3 = 0;
		int totalStudents = 0;
		if(students.size()<0) {
			throw new StudentException("No student is added in system yet.");
		}
		for (Student student : students) {
			for (Subject subject : student.getSubjects()) {
				if (subject.getSemester().equals(semester)) {
					marks1 += subject.getMarks1();
					marks2 += subject.getMarks2(); 
					marks3 += subject.getMarks3();
					totalStudents++;
				}
			}
		}
		String response = "The percentage of marks scored in Subject1 is : " + marks1/totalStudents + "The percentage of marks scored in Subject2 is : " + marks2/totalStudents + "The percentage of marks scored in Subject3 is : " + marks3/totalStudents;
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/top2Students")
	public ResponseEntity<List<Student>> findTopTwoStudentsByMarks() {
	        
		List<Student> allStudents = (List<Student>) studentRepository.findAll();
		
		PriorityQueue<Student> topTwoPriorityQueue = new PriorityQueue<>(2, Comparator.comparingDouble(s -> s.getSubjects().stream().mapToDouble(sub -> sub.getMarks1() + sub.getMarks2() + sub.getMarks3()).sum()));
	        
		for (Student student : allStudents) {
	        topTwoPriorityQueue.offer(student);
	        if (topTwoPriorityQueue.size() > 2) {
	        	topTwoPriorityQueue.poll();
	        }
		}
	        
	   	List<Student> result = new ArrayList<>();
	   	while (!topTwoPriorityQueue.isEmpty()) {
	       	result.add(topTwoPriorityQueue.poll());
	   	}
	   	Collections.reverse(result);
	   	
	   	return new ResponseEntity<List<Student>>(result, HttpStatus.OK);

	}
	
	@PostMapping("/addSemesterMarks/{rollNo}")
	public ResponseEntity<Student> addSemesterAndMarks(@PathVariable("rollNo") Integer rollNo, @RequestBody Subject subject) throws StudentException{
		
		Optional<Student> optionalStudent = studentRepository.findById(rollNo);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
		    student.getSubjects().add(subject);
		    Student saved = studentRepository.save(student);
		    return new ResponseEntity<Student>(saved, HttpStatus.OK);
		} 
		else {
		   	throw new StudentException("Student with roll no " + rollNo + " not found.");
		}
	}

}