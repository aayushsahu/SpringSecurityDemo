package com.solinvictus.SpringSecurityDemo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.solinvictus.SpringSecurityDemo.DTO.CalendarEventDTO;
import com.solinvictus.SpringSecurityDemo.Entity.User;
import com.solinvictus.SpringSecurityDemo.Exceptions.AuthorityException;
import com.solinvictus.SpringSecurityDemo.Exceptions.CalendarEventNotFoundException;
import com.solinvictus.SpringSecurityDemo.Exceptions.InvalidMonthValueException;
import com.solinvictus.SpringSecurityDemo.Exceptions.NoCalendarEventCreatedException;
import com.solinvictus.SpringSecurityDemo.Service.CalendarEventServices;

@RestController
public class CalendarEventController {

	@Autowired
	CalendarEventServices calendarEventServices;

	@PostMapping(path = "/home/addCalendarEvent")
	public @ResponseBody ResponseEntity<Boolean> addCalendarEvent(@AuthenticationPrincipal User user,
			@RequestBody CalendarEventDTO calendarEventDTO) {
		try {
			return new ResponseEntity<Boolean>(calendarEventServices.addCalendarEventService(user, calendarEventDTO),
					HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/home/getAllCalendarEvent")
	public @ResponseBody ResponseEntity<List<CalendarEventDTO>> fetchAllCalendarEvent(
			@AuthenticationPrincipal User user) {
		try {
			return new ResponseEntity<List<CalendarEventDTO>>(calendarEventServices.fetchAllCalendarEventService(user),
					HttpStatus.FOUND);
		} catch (NoCalendarEventCreatedException e) {
			return new ResponseEntity<List<CalendarEventDTO>>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CalendarEventDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/home/getCalendarEvent")
	public @ResponseBody ResponseEntity<CalendarEventDTO> fetchCalendarEvent(@AuthenticationPrincipal User user,
			@RequestParam Optional<Long> id) {
		if (id.isPresent())
			try {
				return new ResponseEntity<CalendarEventDTO>(
						calendarEventServices.fetchCalendarEventByIdService(user, id.get()), HttpStatus.FOUND);
			} catch (AuthorityException e) {
				e.printStackTrace();
				return new ResponseEntity<CalendarEventDTO>(HttpStatus.BAD_REQUEST);
			} catch (CalendarEventNotFoundException e) {
				e.printStackTrace();
				return new ResponseEntity<CalendarEventDTO>(HttpStatus.BAD_REQUEST);
			}
		return new ResponseEntity<CalendarEventDTO>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/home/getCalendarEvents")
	public @ResponseBody ResponseEntity<List<CalendarEventDTO>> fetchCalendarEvent(@AuthenticationPrincipal User user,
			@RequestParam Optional<Integer> date, @RequestParam Optional<String> month,
			@RequestParam Optional<Integer> year) {
		try {
			if (date.isPresent() && month.isPresent() && year.isPresent())
				return new ResponseEntity<List<CalendarEventDTO>>(
						calendarEventServices.fetchCalendarEventService(user, date.get(), month.get(), year.get()),
						HttpStatus.FOUND);
			else if (!date.isPresent() && month.isPresent() && year.isPresent())
				return new ResponseEntity<List<CalendarEventDTO>>(
						calendarEventServices.fetchCalendarEventService(user, month.get(), year.get()),
						HttpStatus.FOUND);
			else if (!date.isPresent() && !month.isPresent() && year.isPresent())
				return new ResponseEntity<List<CalendarEventDTO>>(
						calendarEventServices.fetchCalendarEventService(user, year.get()), HttpStatus.FOUND);
			else
				return new ResponseEntity<List<CalendarEventDTO>>(HttpStatus.BAD_REQUEST);
		} catch (NoCalendarEventCreatedException e) {
			return new ResponseEntity<List<CalendarEventDTO>>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CalendarEventDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

}
