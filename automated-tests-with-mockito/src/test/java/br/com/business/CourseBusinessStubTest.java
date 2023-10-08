package br.com.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.service.CourseService;

class CourseBusinessStubTest {

	CourseService mockService;
	CourseBusiness business;
	
	@BeforeEach
	void setup() {
		// Given / Arrange
		mockService = mock(CourseService.class);
		business = new CourseBusiness(mockService);
	}
	
	@Test
	void testCoursesRelatedToSpring_When_UsingAStub() {
		
		// When / Act
		var filteredCourses = business.retriveCoursesRelatedToSpring("Leandro");
		
		
		// Then / Assert
		assertEquals(4, filteredCourses.size());
	}

	@Test
	void testCoursesRelatedToSpring_When_UsingAFooBarStudent() {
		
		// When / Act
		var filteredCourses = business.retriveCoursesRelatedToSpring("Foo Bar");
		
		
		// Then / Assert
		assertEquals(0, filteredCourses.size());
	}
	
}
