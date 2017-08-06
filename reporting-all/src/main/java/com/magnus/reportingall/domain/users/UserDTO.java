package com.magnus.reportingall.domain.users;

import java.util.ArrayList;
import java.util.List;

import com.magnus.utils.RandomStringUtils;

public class UserDTO {

	private Long id;
	
	private String username;
	
	private List<String> userRoles;
	
	/**
	 * Create and user with username and a random password.
	 * 
	 * @return
	 */
	public User createWORoles() {
		User u = new User();
		u.setUsername(username);
		u.setPassword(RandomStringUtils.createPassword());
		
		return u;
	}

	public static UserDTO create(User u) {
		UserDTO dto = new UserDTO();
		dto.setId(u.getId());
		dto.setUsername(u.getUsername());
		dto.setUserRoles(u.getUserRolesNames());
		
		return dto;
	}

	public static List<UserDTO> createList(List<User> users) {
		List<UserDTO> list = new ArrayList<UserDTO>();
		if (users == null) {
			return list;
		}
		for (User u: users) {
			list.add(UserDTO.create(u));
		}
		
		return list;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
