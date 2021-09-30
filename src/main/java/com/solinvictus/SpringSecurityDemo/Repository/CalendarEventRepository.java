package com.solinvictus.SpringSecurityDemo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solinvictus.SpringSecurityDemo.Entity.CalendarEvent;
import com.solinvictus.SpringSecurityDemo.Entity.Todo;
import com.solinvictus.SpringSecurityDemo.Entity.User;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long>{
	Optional<CalendarEvent> findByCalendarEventId(Long id);
	Optional<List<CalendarEvent>> findAllByUser(User user);
	Optional<List<CalendarEvent>> findAllByEventDateAndUser(String task, User user);
}
