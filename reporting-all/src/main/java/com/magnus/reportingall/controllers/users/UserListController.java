package com.magnus.reportingall.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magnus.reportingall.domain.system.ResponseDTO;
import com.magnus.reportingall.domain.users.UserDTO;
import com.magnus.reportingall.domain.users.UserFilters;
import com.magnus.reportingall.exceptions.InvalidFieldException;
import com.magnus.reportingall.services.users.UsersListService;

@RestController
@RequestMapping(value = "/api/user")
public class UserListController {

	@Autowired
	private UsersListService usersListService;
	
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public ResponseDTO<?> getUsers(@RequestBody UserFilters filters) {
		try {
			filters.setPageSize(10);
			return ResponseDTO.createSuccess(UserDTO.createList(usersListService.getUsers(filters)));
		} catch (InvalidFieldException e) {
			e.printStackTrace();
			return ResponseDTO.createMsgError("Filters are wrong.");
		}
	}
	
}
