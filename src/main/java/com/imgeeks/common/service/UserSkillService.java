package com.imgeeks.common.service;

import java.util.List;

import com.imgeeks.common.bean.UserSkill;

public interface UserSkillService {

	List<UserSkill> getUserKillByUserid(int userid);

	boolean insertSkill(UserSkill userSkill);

	boolean editSkill(UserSkill userSkill);

	boolean delSkill(int id, int userid);

}
