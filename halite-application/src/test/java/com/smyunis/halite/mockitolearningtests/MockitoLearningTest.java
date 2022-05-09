package com.smyunis.halite.mockitolearningtests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MockitoLearningTest {
    @Test
    void mockBasics() {
        List<Integer> mockedList = mock(List.class);

        mockedList.add(898);

        verify(mockedList).add(89);
    }
}
