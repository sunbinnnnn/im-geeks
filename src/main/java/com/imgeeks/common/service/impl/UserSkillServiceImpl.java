package com.imgeeks.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.UserSkill;
import com.imgeeks.common.dao.UserSkillDao;
import com.imgeeks.common.service.UserSkillService;
@Service
public class UserSkillServiceImpl implements UserSkillService {
	@Autowired
	private UserSkillDao userSkillDao;

	@Override
	public List<UserSkill> getUserKillByUserid(int userid) {
		return userSkillDao.getUserKillByUserid(userid);
	}

	@Override
	public boolean insertSkill(UserSkill userSkill) {
		return userSkillDao.insertSkill(userSkill);
	}

	@Override
	public boolean editSkill(UserSkill userSkill) {
		return userSkillDao.editSkill(userSkill);
	}

	@Override
	public boolean delSkill(int id, int userid) {
		return userSkillDao.delSkill(id,userid);
	}
}
