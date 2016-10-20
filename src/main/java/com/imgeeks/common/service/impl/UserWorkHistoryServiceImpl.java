package com.imgeeks.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.UserWorkHistory;
import com.imgeeks.common.dao.UserWorkHistoryDao;
import com.imgeeks.common.service.UserWorkHistoryService;
@Service
public class UserWorkHistoryServiceImpl implements UserWorkHistoryService {
	@Autowired
	private UserWorkHistoryDao userWorkHistoryDao;

	@Override
	public List<UserWorkHistory> getUserWorkHistoryByUserid(int userid) {
		return userWorkHistoryDao.getUserWorkHistoryByUserid(userid);
	}

	@Override
	public boolean insertWorkHhistory(UserWorkHistory userWorkHistory) {
		return userWorkHistoryDao.insertWorkHhistory(userWorkHistory);
	}

	@Override
	public boolean delUserWork(int id, int userid) {
		
		return userWorkHistoryDao.delUserWork(id,userid);
	}

	@Override
	public boolean updatetWorkHistory(UserWorkHistory userWorkHistory) {
		return userWorkHistoryDao.updatetWorkHistory(userWorkHistory);
	}

	@Override
	public UserWorkHistory getUserWorkHistoryById(int id) {
		return userWorkHistoryDao.getUserWorkHistoryById(id);
	}
}
