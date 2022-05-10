package com.smyunis.halite.mockitolearningtests;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*
*  docs can be found at : https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html
* */

public class MockitoLearningTest {
    @Test
    void mockBasics() {
        List<Integer> mockedList = mock(List.class);

        mockedList.add(89);

        // Verifies this method was called
        verify(mockedList).add(89);
    }

    @Test
    void stubAMethodReturn() {
        List<Integer> mockedList = mock(List.class);
        when(mockedList.get(2)).thenReturn(63);

        assertEquals(63,mockedList.get(2));
    }

    @Test
    void stubWithArgMatchers() {
        List<Integer> mockedList = mock(List.class);
        when(mockedList.get(anyInt())).thenReturn(23);

        assertEquals(23,mockedList.get(5));
        verify(mockedList).get(anyInt());
    }

    @Test
    void throwsForInvalidParam() {
        List<Integer> mockedList = mock(List.class);
        when(mockedList.get(10000)).thenThrow(IndexOutOfBoundsException.class);

        assertThrows(IndexOutOfBoundsException.class,() -> {
           mockedList.get(10000);
        });
        verify(mockedList).get(10000);
    }

    @Test
    void stubVoidMethodsThatThrow() {
        List<Integer> mockedList = mock(List.class);
        doThrow(IndexOutOfBoundsException.class).when(mockedList).clear();

        assertThrows(IndexOutOfBoundsException.class, mockedList::clear);
    }
}
