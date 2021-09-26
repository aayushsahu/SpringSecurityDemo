package com.solinvictus.SpringSecurityDemo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solinvictus.SpringSecurityDemo.Entity.Todo;
import com.solinvictus.SpringSecurityDemo.Entity.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
	List<Todo> findAllByUser(User user);
	Optional<List<Todo>> findAllByTaskAndUser(String task, User user);
	//Todo findByTodoId(Long id);
}
