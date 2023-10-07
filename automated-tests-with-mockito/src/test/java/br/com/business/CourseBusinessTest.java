package br.com.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.service.CourseService;
import br.com.service.stubs.CourseServiceStub;

class CourseBusinessTest {

	@Test
	void testCoursesRelatedToSpring_When_UsingAStub() {
		// Given / Arrange
		CourseService stubService = new CourseServiceStub();
		CourseBusiness business = new CourseBusiness(stubService);
		
		// When / Act
		var filteredCourses = business.retriveCoursesRelatedToSpring("Leandro");
		
		
		// Then / Assert
		assertEquals(4, filteredCourses.size());
	}

	@Test
	void testCoursesRelatedToSpring_When_UsingAFooBarStudent() {
		// Given / Arrange
		CourseService stubService = new CourseServiceStub();
		CourseBusiness business = new CourseBusiness(stubService);
		
		// When / Act
		var filteredCourses = business.retriveCoursesRelatedToSpring("Foo Bar");
		
		
		// Then / Assert
		assertEquals(0, filteredCourses.size());
	}
	
}
