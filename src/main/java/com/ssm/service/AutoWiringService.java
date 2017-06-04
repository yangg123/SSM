package com.ssm.service;

import com.ssm.dao.AutoWiringDAO;

public class AutoWiringService {
	
	private AutoWiringDAO autoWiringDAO;

	public void setAutoWiringDAO(AutoWiringDAO autoWiringDAO) {
		System.out.println("setAutoWiringDAO - by name");
		this.autoWiringDAO = autoWiringDAO;
	}

	public AutoWiringService(AutoWiringDAO autoWiringDAO) {
		System.out.println("AutoWiringService- by constructor");
		this.autoWiringDAO = autoWiringDAO;
	}

	public void say(String word) {
		this.autoWiringDAO.say(word);
	}

}
