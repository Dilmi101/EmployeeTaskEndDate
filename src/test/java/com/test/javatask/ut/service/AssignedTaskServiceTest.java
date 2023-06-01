package com.test.javatask.ut.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;


import com.test.javatask.service.AssignedTaskServiceImpl;


public class AssignedTaskServiceTest {
	
	@InjectMocks
	AssignedTaskServiceImpl assignedTaskServiceImpl = PowerMockito.spy(new AssignedTaskServiceImpl());

    @Test
    public void test_calculateEndDateOfTheTask() throws Exception{
    	

        String actual = assignedTaskServiceImpl.calculateEndDateOfTheTask("02-06-2023", 5);

        assertNotNull(actual);
        assertEquals("09-06-2023", actual);

    }
}
