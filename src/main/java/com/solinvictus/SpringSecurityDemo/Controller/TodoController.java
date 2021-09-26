package com.solinvictus.SpringSecurityDemo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.solinvictus.SpringSecurityDemo.DTO.TodoDTO;
import com.solinvictus.SpringSecurityDemo.Entity.Todo;
import com.solinvictus.SpringSecurityDemo.Entity.User;
import com.solinvictus.SpringSecurityDemo.Exceptions.AuthorityException;
import com.solinvictus.SpringSecurityDemo.Exceptions.TodoNotFoundException;
import com.solinvictus.SpringSecurityDemo.Service.TodoService;

@RestController
public class TodoController {

	@Autowired
	TodoService todoService;

	@PostMapping(path = "/home/addTodo")
	public @ResponseBody Boolean addTodo(@AuthenticationPrincipal User user,
			@RequestBody Map<String, String> inputMap) {
		return todoService.addTodoService(user, inputMap.get("inpTodoTask"));
	}

	@GetMapping(path = "/home/myTodos")
	public @ResponseBody List<Todo> fetchTodo(@AuthenticationPrincipal User user) {
		return todoService.fetchAllTodoService(user);
	}

	@GetMapping(path = "/home/myTodo")
	public @ResponseBody ResponseEntity<Todo> fetchTodoById(@AuthenticationPrincipal User user, @RequestParam long id) {
		try {
			return new ResponseEntity<Todo>(todoService.fetchTodoByIdService(user, id), HttpStatus.FOUND);
		} catch (AuthorityException e) {
			e.printStackTrace();
			return new ResponseEntity<Todo>(HttpStatus.FORBIDDEN);
		} catch (TodoNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/home/myTodo")
	public @ResponseBody ResponseEntity<Boolean> markTodoAsDone(@AuthenticationPrincipal User user,
			@RequestBody Map<String, Object> inputMap) {
		try {
			return new ResponseEntity<Boolean>(todoService.changeTodoToDoneService(user,
					Long.valueOf(inputMap.get("taskId").toString()), (Boolean) inputMap.get("changeTaskStatus")),
					HttpStatus.FOUND);
		} catch (AuthorityException e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.FORBIDDEN);
		} catch (TodoNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/home/myTodo2")
	public @ResponseBody ResponseEntity<Boolean> markTodoAsDone2(@AuthenticationPrincipal User user,
			@RequestBody TodoDTO todoDto) {
		try {
			return new ResponseEntity<Boolean>(todoService.changeTodoToDone2Service(user, todoDto),
					HttpStatus.FOUND);
//		} catch (AuthorityException e) {
//			e.printStackTrace();
//			return new ResponseEntity<Boolean>(HttpStatus.FORBIDDEN);
		} catch (TodoNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/home/deleteDoneTodos")
	public @ResponseBody ResponseEntity<Boolean> deleteDoneTodos(@AuthenticationPrincipal User user) {
		return new ResponseEntity<Boolean>(todoService.deleteDoneTodosService(user), HttpStatus.OK);
	}

	@DeleteMapping(path = "/home/deleteDoneTodo")
	public @ResponseBody ResponseEntity<Boolean> deleteTodo(@AuthenticationPrincipal User user, @RequestBody long id) {
		try {
			return new ResponseEntity<Boolean>(todoService.deleteTodoService(user, id), HttpStatus.OK);
		} catch (AuthorityException e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.FORBIDDEN);
		} catch (TodoNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
	}
}
