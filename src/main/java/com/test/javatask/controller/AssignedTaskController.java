package com.test.javatask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.javatask.service.AssignedTaskService;

@RestController
@RequestMapping("/task")
public class AssignedTaskController {
	
	@Autowired
	private AssignedTaskService assignedTaskService;

	@RequestMapping(value = "/{customerId}/endDate" , method = RequestMethod.GET)
	public String calculateEndDateOfTheTask(@PathVariable("customerId") int customerId, 
			@RequestParam(value="startDate", required=true) String startDate,
			@RequestParam(value="noOfDays", required=true) int noOfDays) {
		
		String endDate = assignedTaskService.calculateEndDateOfTheTask(startDate, noOfDays);
		
		return endDate;
		
	}
}
