package com.magnus.reportingall.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magnus.reportingall.domain.system.SystemDTO;
import com.magnus.utils.ApplicationProperties;

@RestController
@RequestMapping(value = "/api/index")
public class IndexController {

	@RequestMapping(value = "/isOn", method = {RequestMethod.GET})
	public String isOn() {
		return "Reporting All is ON!";
	}
	
	@RequestMapping(value = "/version", method = {RequestMethod.GET})
	public SystemDTO getVersion() {
		return new SystemDTO(ApplicationProperties.getProperty("application.name"), ApplicationProperties.getProperty("application.version"));
	}
	
}
