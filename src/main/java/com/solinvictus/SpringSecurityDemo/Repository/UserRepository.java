package com.solinvictus.SpringSecurityDemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solinvictus.SpringSecurityDemo.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	//works automatically 
	User findByUsername(String username);
}
