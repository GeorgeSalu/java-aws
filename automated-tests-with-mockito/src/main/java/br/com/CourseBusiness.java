package br.com;

import java.util.ArrayList;
import java.util.List;

import br.com.service.CourseService;

// System ()Method Under Test
public class CourseBusiness {

	private CourseService service;

	public CourseBusiness(CourseService service) {
		this.service = service;
	}
	
	public List<String> retriveCoursesRelatedToSpring(String student) {
		var filteredCourses = new ArrayList<String>();
		var allCourses = service.retrieveCourses(student);
		
		for (String course : allCourses) {
			if(course.contains("Spring")) {
				filteredCourses.add(course);
			}
		}
		
		return filteredCourses;
	}
	
}
