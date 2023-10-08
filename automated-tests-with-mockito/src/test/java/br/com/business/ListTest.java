package br.com.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {

	@Test
	void testMockingList_When_SizeIsCalled_ShouldReturn10() {
		// Given / Arrange
		List<?> list = mock(List.class);
		given(list.size()).willReturn(10);
		
		// When / Act && Then / Assert
		
		assertThat(list.size(), is(10));
	}
	
	@Test
	void testMockingList_When_SizeIsCalled_ShouldReturnMultiplesValues() {
		// Given / Arrange
		List<?> list = mock(List.class);
		given(list.size()).willReturn(10).willReturn(20);
		
		// When / Act && Then / Assert
		
		assertThat(list.size(), is(10));
		assertThat(list.size(), is(20));
		assertThat(list.size(), is(20));
	}
	
	@Test
	void testMockingList_When_SizeIsCalled_ShouldReturnGeorge() {
		// Given / Arrange
		var list = mock(List.class);
		given(list.get(0)).willReturn("George");
		
		// When / Act && Then / Assert
		
		assertThat(list.get(0), is("George"));
	}
	
	@Test
	void testMockingList_When_GetISCalledWithArgumentMatcher_ShouldReturnGeorge() {
		// Given / Arrange
		var list = mock(List.class);
		
		// if uou are using argument matchers, all arguments
		// have to be provided by matchers
		given(list.get(anyInt())).willReturn("George");
		
		// When / Act && Then / Assert
		
		assertThat(list.get(anyInt()), is("George"));
	}
	
	@Test
	void testMockingList_When_ThrowsAnException() {
		// Given / Arrange
		var list = mock(List.class);
		
		// if uou are using argument matchers, all arguments
		// have to be provided by matchers
		given(list.get(anyInt())).willThrow(new RuntimeException("Foo Bar"));
		
		// When / Act && Then / Assert
		assertThrows(RuntimeException.class, () -> {
			list.get(anyInt());
		}, () -> "Should have throw an RuntimeException");
	}
	
}
