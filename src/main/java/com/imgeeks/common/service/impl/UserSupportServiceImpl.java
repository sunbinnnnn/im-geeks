package com.imgeeks.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.User;
import com.imgeeks.common.dao.UserSupportDao;
import com.imgeeks.common.service.UserSupportService;
@Service
public class UserSupportServiceImpl implements UserSupportService {
	@Autowired
	private UserSupportDao userSupportDao;

	@Override
	public boolean isSupportUser(int userid, int otherid) {
		return userSupportDao.isSupportUser(userid,otherid);
	}

	@Override
	public boolean insertSupportUser(int userid, int otherid) {
		return  userSupportDao.insertSupportUser(userid,otherid);
	}

	@Override
	public List<Map> whoSupportMe(int userid, int pageno) {
		return userSupportDao.whoSupportMe(userid,pageno);
	}
}
