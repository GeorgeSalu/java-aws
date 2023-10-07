package br.com.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.service.CourseService;
import br.com.service.stubs.CourseServiceStub;

class CourseBusinessTest {

	@Test
	void testCoursesRelatedToSpring_When_UsingAStub() {
		CourseService stubService = new CourseServiceStub();
		CourseBusiness business = new CourseBusiness(stubService);
		
		var filteredCourses = business.retriveCoursesRelatedToSpring("Leandro");
		
		
		assertEquals(4, filteredCourses.size());
	}

	@Test
	void testCoursesRelatedToSpring_When_UsingAFooBarStudent() {
		CourseService stubService = new CourseServiceStub();
		CourseBusiness business = new CourseBusiness(stubService);
		
		var filteredCourses = business.retriveCoursesRelatedToSpring("Foo Bar");
		
		
		assertEquals(0, filteredCourses.size());
	}
	
}
