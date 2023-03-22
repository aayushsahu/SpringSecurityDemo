package com.solinvictus.SpringSecurityDemo.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solinvictus.SpringSecurityDemo.DTO.CalendarEventDTO;
import com.solinvictus.SpringSecurityDemo.Entity.CalendarEvent;
import com.solinvictus.SpringSecurityDemo.Entity.Todo;
import com.solinvictus.SpringSecurityDemo.Entity.User;
import com.solinvictus.SpringSecurityDemo.Exceptions.AuthorityException;
import com.solinvictus.SpringSecurityDemo.Exceptions.CalendarEventNotFoundException;
import com.solinvictus.SpringSecurityDemo.Exceptions.InvalidMonthValueException;
import com.solinvictus.SpringSecurityDemo.Exceptions.NoCalendarEventCreatedException;
import com.solinvictus.SpringSecurityDemo.Repository.CalendarEventRepository;
import com.solinvictus.SpringSecurityDemo.Util.CalenderUtility;

@Service
public class CalendarEventServices {

	@Autowired
	private CalendarEventRepository calendarEventRepository;

	@Autowired
	private ModelMapper modelMapper;

	public boolean addCalendarEventService(User user, CalendarEventDTO calendarEventDTO) {
		try {
			CalendarEvent calendarEvent = modelMapper.map(calendarEventDTO, CalendarEvent.class);
			calendarEvent.setUser(user);
			calendarEventRepository.save(calendarEvent);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public CalendarEventDTO fetchCalendarEventByIdService(User user, Long id)
			throws AuthorityException, CalendarEventNotFoundException {

		CalendarEventDTO calendarEventDTO;
		Optional<CalendarEvent> calendarEvent = calendarEventRepository.findByCalendarEventId(id);
		
		if (calendarEvent.isPresent()) {
			if (calendarEvent.get().getUser().getUsername().equals(user.getUsername())) {
				modelMapper.typeMap(CalendarEvent.class, CalendarEventDTO.class)
					.addMapping(CalendarEvent::getDateKeeper, CalendarEventDTO::setDateKeeper)
					.addMapping(CalendarEvent::getEventDate, CalendarEventDTO::setEventDate)
					.addMapping(CalendarEvent::getEventDescription, CalendarEventDTO::setEventDescription)
					.addMapping(CalendarEvent::getNoteForEvent, CalendarEventDTO::setNoteForEvent);
				calendarEventDTO = modelMapper.map(calendarEvent.get(), CalendarEventDTO.class);
				return calendarEventDTO;
			} else
				throw new AuthorityException("User is not authorized to view CalendarEvent with id :" + id);
		} else
			throw new CalendarEventNotFoundException("No such calendar event found");
	}

	public List<CalendarEventDTO> fetchAllCalendarEventService(User user) throws NoCalendarEventCreatedException {
		List<CalendarEventDTO> listOfCalendarEventsDTO = new ArrayList<>();
		Optional<List<CalendarEvent>> listOfCalendarEvents = calendarEventRepository.findAllByUser(user);
		
		modelMapper.typeMap(CalendarEvent.class, CalendarEventDTO.class)
				.addMapping(CalendarEvent::getDateKeeper, CalendarEventDTO::setDateKeeper)
				.addMapping(CalendarEvent::getEventDate, CalendarEventDTO::setEventDate)
				.addMapping(CalendarEvent::getEventDescription, CalendarEventDTO::setEventDescription)
				.addMapping(CalendarEvent::getNoteForEvent, CalendarEventDTO::setNoteForEvent);
		
		if (listOfCalendarEvents.isPresent()) {
			for (CalendarEvent calendarEvent : listOfCalendarEvents.get())
				listOfCalendarEventsDTO.add(modelMapper.map(calendarEvent, CalendarEventDTO.class));
		} else {
			throw new NoCalendarEventCreatedException(
					"No Calendar Event created yet. Please create an event on calendar");
		}
		return listOfCalendarEventsDTO;
	}

	public List<CalendarEventDTO> fetchCalendarEventService(User user, int year)
			throws NoCalendarEventCreatedException {
		List<CalendarEventDTO> listOfCalendarEventsDTO = this.fetchAllCalendarEventService(user);
		
		modelMapper.typeMap(CalendarEvent.class, CalendarEventDTO.class)
				.addMapping(CalendarEvent::getDateKeeper, CalendarEventDTO::setDateKeeper)
				.addMapping(CalendarEvent::getEventDate, CalendarEventDTO::setEventDate)
				.addMapping(CalendarEvent::getEventDescription, CalendarEventDTO::setEventDescription)
				.addMapping(CalendarEvent::getNoteForEvent, CalendarEventDTO::setNoteForEvent);
		
		listOfCalendarEventsDTO = listOfCalendarEventsDTO.stream()
				.filter(ceDTO -> ceDTO.getEventDate().getYear() + 1900 == year).collect(Collectors.toList());

		return listOfCalendarEventsDTO;
	}

	public List<CalendarEventDTO> fetchCalendarEventService(User user, String month, int year)
			throws NoCalendarEventCreatedException, InvalidMonthValueException {
		List<CalendarEventDTO> listOfCalendarEventsDTO = this.fetchAllCalendarEventService(user);

		int mon = CalenderUtility.monthStrToNum(month);
		
		modelMapper.typeMap(CalendarEvent.class, CalendarEventDTO.class)
				.addMapping(CalendarEvent::getDateKeeper, CalendarEventDTO::setDateKeeper)
				.addMapping(CalendarEvent::getEventDate, CalendarEventDTO::setEventDate)
				.addMapping(CalendarEvent::getEventDescription, CalendarEventDTO::setEventDescription)
				.addMapping(CalendarEvent::getNoteForEvent, CalendarEventDTO::setNoteForEvent);
		
		listOfCalendarEventsDTO = listOfCalendarEventsDTO.stream()
				.filter(ceDTO -> ceDTO.getEventDate().getYear() + 1900 == year)
				.filter(ceDTO -> ceDTO.getEventDate().getMonth() + 1 == mon).collect(Collectors.toList());

		return listOfCalendarEventsDTO;
	}

	public List<CalendarEventDTO> fetchCalendarEventService(User user, int date, String month, int year)
			throws NoCalendarEventCreatedException, InvalidMonthValueException {
		List<CalendarEventDTO> listOfCalendarEventsDTO = this.fetchAllCalendarEventService(user);

		int mon = CalenderUtility.monthStrToNum(month);
		
		modelMapper.typeMap(CalendarEvent.class, CalendarEventDTO.class)
				.addMapping(CalendarEvent::getDateKeeper, CalendarEventDTO::setDateKeeper)
				.addMapping(CalendarEvent::getEventDate, CalendarEventDTO::setEventDate)
				.addMapping(CalendarEvent::getEventDescription, CalendarEventDTO::setEventDescription)
				.addMapping(CalendarEvent::getNoteForEvent, CalendarEventDTO::setNoteForEvent);

		listOfCalendarEventsDTO = listOfCalendarEventsDTO.stream()
				.filter(ceDTO -> ceDTO.getEventDate().getYear() + 1900 == year)
				.filter(ceDTO -> ceDTO.getEventDate().getMonth() + 1 == mon)
				.filter(ceDTO -> ceDTO.getEventDate().getDate() == date).collect(Collectors.toList());

		return listOfCalendarEventsDTO;
	}

}
