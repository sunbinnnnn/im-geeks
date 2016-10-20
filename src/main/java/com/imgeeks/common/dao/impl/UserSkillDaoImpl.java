package com.imgeeks.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.UserSkill;
import com.imgeeks.common.dao.UserSkillDao;
@Component
public class UserSkillDaoImpl extends BaseDao  implements UserSkillDao {

	@Override
	public List<UserSkill> getUserKillByUserid(int userid) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		List<UserSkill> selectList = super.getSqlSession().selectList("userskill.getUserSkillByUserid",map);
		return selectList;
	}

	@Override
	public boolean insertSkill(UserSkill userSkill) {
		int insert = super.getSqlSession().insert("userskill.insertSkill", userSkill);
		return insert>0?true:false;
	}

	@Override
	public boolean editSkill(UserSkill userSkill) {
	
		int update = super.getSqlSession().update("userskill.updateSkill", userSkill);
		return update>0?true:false;
	}

	@Override
	public boolean delSkill(int id,int userid) {
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("userid", userid);
		int delete = super.getSqlSession().delete("userskill.delSkill", map);
		return delete>0?true:false;
	}

	@Override
	public Integer getUserIdByKill(UserSkill userSkill) {
		Map<String ,String> map = new HashMap<String,String>();
		map.put("proficiency", userSkill.getProficiency());
		map.put("userkill", userSkill.getUserskill());
		List<Object> selectList = super.getSqlSession().selectList("userskill.getUserIdByKill", map);
		int id = 0;
		if(selectList.size()>0){
			id = (int)selectList.get(0);
		}
		return id;
	}

}
