package com.magnus.reportingall.services.users;

import com.magnus.reportingall.domain.users.User;
import com.magnus.reportingall.domain.users.UserDTO;
import com.magnus.reportingall.exceptions.ServiceException;

public interface UsersService {

	/**
	 * 
	 * Find user using id as ID.
	 * 
	 * @param id
	 * @return
	 */
	User findById(Long id);

	/**
	 * 
	 * Save the user with dto information.
	 * 
	 * @param dto
	 * @return
	 */
	Long save(UserDTO dto);
	
	/**
	 * 
	 * Delete the user used by id.
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	void delete(Long id) throws ServiceException;
	
}
