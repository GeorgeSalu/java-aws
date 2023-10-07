package br.com.service;

import java.util.List;

public interface CourseService {

	public List<String> retrieveCourses(String student);
	public List<String> doSomeThing(String student);
	
}
