package com.solinvictus.SpringSecurityDemo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solinvictus.SpringSecurityDemo.DTO.TodoDTO;
import com.solinvictus.SpringSecurityDemo.Entity.Authorities;
import com.solinvictus.SpringSecurityDemo.Entity.Todo;
import com.solinvictus.SpringSecurityDemo.Entity.User;
import com.solinvictus.SpringSecurityDemo.Exceptions.AuthorityException;
import com.solinvictus.SpringSecurityDemo.Exceptions.TodoNotFoundException;
import com.solinvictus.SpringSecurityDemo.Repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	TodoRepository todoRepository;

	public boolean addTodoService(User user, String todoTask) {
		try {
			Todo todo = new Todo(todoTask, user);
			todoRepository.save(todo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Todo> fetchAllTodoService(User user) {
		List<Todo> listOfTodos = new ArrayList<>();
		try {
			listOfTodos = todoRepository.findAllByUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return listOfTodos;
		}
		return listOfTodos;
	}

	public Todo fetchTodoByIdService(User user, long id) throws AuthorityException, TodoNotFoundException {

		Optional<Todo> todo = todoRepository.findById(id);

		if (todo.isPresent()) {
			if (user.getAuthorities().contains(new Authorities("ADMIN"))
					|| user.getUserId() == todo.get().getUser().getUserId()) {
				System.out.println("Todo: " + todo);
				System.out.println("User: " + user);
				return todo.get();
			}
			throw new AuthorityException("User is not authorized to view Todo with id :" + id);
		}
		throw new TodoNotFoundException("Invalid id:" + id + "for Todo item");
		
	}

	public boolean changeTodoToDoneService(User user, long id, boolean changeIsDoneStatus)
			throws AuthorityException, TodoNotFoundException {

		Todo todo = this.fetchTodoByIdService(user, id);

		if (todo != null) {
			todo.setDone(!todo.isDone());
			todoRepository.save(todo);
			return true;
		}
		return false;
	}

	public Boolean changeTodoToDone2Service(User user, TodoDTO todoDto)
			throws TodoNotFoundException {
		Optional<List<Todo>> todosByTask = todoRepository.findAllByTaskAndUser(todoDto.getTask(), user);
		if (todosByTask.isPresent()) {
			todosByTask.get().stream().forEach(t-> {
				t.setDone(todoDto.isDone());
				todoRepository.save(t);
			});
			return true;
		}
		else
			throw new TodoNotFoundException("Invalid task : " + todoDto.getTask());
	}

	public boolean deleteDoneTodosService(User user) {

		try {
			this.fetchAllTodoService(user).stream().filter(todo -> todo.isDone() == true)
				// Approach I
				.forEach((Todo t) -> todoRepository.delete(t));
				// Approach II
				// .forEach(new Consumer<Todo>() {
				// 	@Override
				// 	public void accept(Todo t) {
				// 		todoRepository.delete(t);
				//
				// }
				// });
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteTodoService(User user, long id) throws AuthorityException, TodoNotFoundException {

		Optional<Todo> todo = todoRepository.findById(id);

		if (todo.isPresent()) {
			if (todo.get().getUser().getUserId() == user.getUserId()) {
				todoRepository.deleteById(id);
				return true;
			} else {
				throw new AuthorityException("User is not authorized to delete Todo with id :" + id);
			}
		} else {
			throw new TodoNotFoundException("Invalid id:" + id + "for Todo item");
		}
	}

}
