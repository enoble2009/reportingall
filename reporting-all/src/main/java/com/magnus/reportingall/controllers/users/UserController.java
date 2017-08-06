package com.magnus.reportingall.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magnus.reportingall.domain.system.ResponseDTO;
import com.magnus.reportingall.domain.users.UserDTO;
import com.magnus.reportingall.domain.users.UserLoginDTO;
import com.magnus.reportingall.services.users.UserSessionsService;
import com.magnus.reportingall.services.users.UsersService;

@RestController
public class UserController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UserSessionsService userSessionsService;
	
	@RequestMapping(value = "/get/{id}", method = {RequestMethod.GET})
	public ResponseDTO<?> getUser(@PathVariable(name = "id") Long id) {
		return ResponseDTO.createSuccess(UserDTO.create(usersService.findById(id)));
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public ResponseDTO<?> getUser(@RequestBody UserLoginDTO loginDTO) {
		return ResponseDTO.createSuccess(userSessionsService.login(loginDTO));
	}
	
}
