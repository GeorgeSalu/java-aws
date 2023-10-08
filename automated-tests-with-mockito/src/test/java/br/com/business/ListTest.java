package br.com.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {

	@Test
	void testMockingList_When_SizeIsCalled_ShouldReturn10() {
		// Given / Arrange
		List<?> list = mock(List.class);
		when(list.size()).thenReturn(10);
		
		// When / Act && Then / Assert
		
		assertEquals(10, list.size());
	}
	
	@Test
	void testMockingList_When_SizeIsCalled_ShouldReturnMultiplesValues() {
		// Given / Arrange
		List<?> list = mock(List.class);
		when(list.size()).thenReturn(10).thenReturn(20);
		
		// When / Act && Then / Assert
		
		assertEquals(10, list.size());
		assertEquals(20, list.size());
		assertEquals(20, list.size());
	}
	
	@Test
	void testMockingList_When_SizeIsCalled_ShouldReturnGeorge() {
		// Given / Arrange
		var list = mock(List.class);
		when(list.get(0)).thenReturn("George");
		
		// When / Act && Then / Assert
		
		assertEquals("George", list.get(0));
	}
	
	@Test
	void testMockingList_When_GetISCalledWithArgumentMatcher_ShouldReturnGeorge() {
		// Given / Arrange
		var list = mock(List.class);
		
		// if uou are using argument matchers, all arguments
		// have to be provided by matchers
		when(list.get(anyInt())).thenReturn("George");
		
		// When / Act && Then / Assert
		
		assertEquals("George", list.get(anyInt()));
	}
	
}
