package com.imgeeks.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.dao.HoyoDao;
import com.imgeeks.common.service.HoyoService;
@Service
public class HoyoServiceImpl implements HoyoService {
	@Autowired
	private HoyoDao hoyoDao;

	@Override
	public boolean checkFriend(int currentuserid, String username) {
		return hoyoDao.checkFriend(currentuserid,username);
	}

	@Override
	public boolean addFriend(int currentuserid, String username,String roomid) {
		return hoyoDao.addFriend(currentuserid,username,roomid);
	}
}
