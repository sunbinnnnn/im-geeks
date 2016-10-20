package com.imgeeks.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.UserEducation;
import com.imgeeks.common.dao.UserEducationDao;
import com.imgeeks.common.service.UserEducationService;
@Service
public class UserEducationServiceImpl implements UserEducationService {
	@Autowired
	private UserEducationDao userEducationDao;

	@Override
	public List<UserEducation> getUserEducationByUserid(int userid) {
		return userEducationDao.getUserEducationByUserid(userid);
	}

	@Override
	public boolean insertUserEducation(UserEducation userEducation) {
		return userEducationDao.insertUserEducation(userEducation);
	}

	@Override
	public boolean deluserEdu(int id, int userid) {
		return userEducationDao.deluserEdu(id,userid);
	}

	@Override
	public boolean updatetUserEdu(UserEducation userEducation) {
		return userEducationDao.updatetUserEdu(userEducation);
	}

	@Override
	public UserEducation getUserEduById(int id) {
		return userEducationDao.getUserEduById(id);
	}
}
