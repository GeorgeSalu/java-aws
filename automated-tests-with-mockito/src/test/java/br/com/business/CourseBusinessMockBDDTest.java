package br.com.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.mockito.BDDMockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import br.com.service.CourseService;

class CourseBusinessMockBDDTest {

	CourseService mockService;
	CourseBusiness business;
	List<String> courses;
	
	@BeforeEach
	void setup() {
		// Given / Arrange
		mockService = mock(CourseService.class);
		business = new CourseBusiness(mockService);
		courses = Arrays.asList(
				"REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
				);
	}
	
	@Test
	void testCoursesRelatedToSpring_When_UsingAMock() {
		// Given / Arrange
		
		when(mockService.retrieveCourses("Leandro")).thenReturn(courses);
		
		
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
	
	@DisplayName("Delete courses not related to Spring Usinng Mockito sloud call method")
	@Test
	void testDeleteCoursesNotRelatedToSpring_usingMockitoVerify_Should_CallMethod_deleteCourse() {
		// Give / Arrange
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		// When / Act
		business.deleteCourseNotRelatedToSpring("Leandro");
		
		// Then / Assert
		// similar ao times(1)
		verify(mockService, atLeastOnce()).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		// times() = chama uma numero determinado de vezes que é passado como paramentro
		verify(mockService, times(1)).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		verify(mockService).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		// never() = nunca foi chamado
		verify(mockService, never()).deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
	}
	
	@DisplayName("Delete courses not related to Spring Usinng Mockito sloud call method V2")
	@Test
	void testDeleteCoursesNotRelatedToSpring_usingMockitoVerify_Should_CallMethod_deleteCourseV2() {
		// Give / Arrange
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
		String architectureCourse = "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#";
		String restSpringCourse = "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker";
		
		// When / Act
		business.deleteCourseNotRelatedToSpring("Leandro");
		
		// Then / Assert

		then(mockService).should().deleteCourse(agileCourse);
		then(mockService).should().deleteCourse(architectureCourse);
		then(mockService).should(never()).deleteCourse(restSpringCourse);
	}
	
	@DisplayName("Delete courses not related to Spring Capturing Arguments sloud call method")
	@Test
	void testDeleteCoursesNotRelatedToSpring_CapturingArguments_Should_CallMethod_deleteCourseV2() {
		// Give / Arrange
		courses = Arrays.asList(
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker"
				);
		
		
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		
		String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
		
		// When / Act
		business.deleteCourseNotRelatedToSpring("Leandro");
		
		// Then / Assert

		then(mockService).should().deleteCourse(argumentCaptor.capture());
		
		assertThat(argumentCaptor.getValue(), is(agileCourse));
	}
	
}













