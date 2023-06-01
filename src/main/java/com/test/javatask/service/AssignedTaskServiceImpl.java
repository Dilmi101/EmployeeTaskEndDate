package com.test.javatask.service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class AssignedTaskServiceImpl implements AssignedTaskService {
	
	private static final String DATE_OF_THE_PUBLIC_HOLIDAY = "06-06-2023";

	/*
	 * Assumptions made:
	 * there's only 1 public holiday
	 * public holiday is within the days allocated for the task
	 */
	@Override
	public String calculateEndDateOfTheTask(String startDate, int noOfDays) {
		
		try {
			
	        int initialDays = noOfDays;

	        // Default time zone
	        ZoneId defaultZoneId = ZoneId.systemDefault();

	        Date strtDate = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);

	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(strtDate);

	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH);
	        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

	        // Define the start date
	        LocalDate sDate = LocalDate.of(year, month + 1, dayOfMonth);

	        // Add X no of days to the start date
	        LocalDate dateAfterXNoOfDays = sDate.plusDays(noOfDays);

	        Date holiday = new SimpleDateFormat("dd-MM-yyyy").parse(DATE_OF_THE_PUBLIC_HOLIDAY);
	        Date dafd = Date.from(dateAfterXNoOfDays.atStartOfDay(defaultZoneId).toInstant());
	        
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        String date = simpleDateFormat.format(dafd);
	        dafd = new SimpleDateFormat("dd-MM-yyyy").parse(date);
	        
	        if(holiday.after(strtDate) && holiday.before(dafd)) {
	        	noOfDays = noOfDays + 1;
	        }
	        
	        // Checks if weekends fall in between days
	        for (int i = 0; i < initialDays; i++) {
	            sDate = sDate.plusDays(1);

	            if (sDate.getDayOfWeek() == DayOfWeek.SATURDAY || sDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
	                noOfDays = noOfDays + 1;
	            }
	        }
			
	        LocalDate actualEndDate = sDate.plusDays(noOfDays);
	        
	        return actualEndDate.toString();
	        
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid date";
		}


	}

	
}
