package com.imgeeks.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.dao.HobbyDao;
import com.imgeeks.common.service.HobbyService;
@Service
public class HobbyServiceImpl implements HobbyService {
	@Autowired
	private HobbyDao hobbyDao;
	@Override
	public List getAllHobby() {
		return hobbyDao.getAllHobby();
	}

}
