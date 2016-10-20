package com.imgeeks.common.dao;

import java.util.List;

import com.imgeeks.common.bean.UserSkill;

public interface UserSkillDao {

	List<UserSkill> getUserKillByUserid(int userid);

	boolean insertSkill(UserSkill userSkill);

	boolean editSkill(UserSkill userSkill);

	boolean delSkill(int id,int userid);

	Integer getUserIdByKill(UserSkill userSkill);

}
