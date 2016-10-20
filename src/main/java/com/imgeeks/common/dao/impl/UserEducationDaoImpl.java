package com.imgeeks.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.UserEducation;
import com.imgeeks.common.dao.UserEducationDao;
@Component
public class UserEducationDaoImpl extends BaseDao  implements UserEducationDao {

	@Override
	public List<UserEducation> getUserEducationByUserid(int userid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		List<UserEducation> userEducationList = super.getSqlSession().selectList("usereducation.getUserEducationByUserid", map);
		return userEducationList;
	}

	@Override
	public boolean insertUserEducation(UserEducation userEducation) {
		int insert = super.getSqlSession().insert("usereducation.insertUserEducation", userEducation);
		return insert>0?true:false;
	}

	@Override
	public boolean deluserEdu(int id, int userid) {
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("userid", userid);
		int delete = super.getSqlSession().delete("usereducation.delUserEducation", map);
		return delete>0?true:false;
	}

	@Override
	public boolean updatetUserEdu(UserEducation userEducation) {
		int update = super.getSqlSession().update("usereducation.updateUserEdu", userEducation);
		return update>0?true:false;
	}

	@Override
	public UserEducation getUserEduById(int id) {
		UserEducation selectOne = super.getSqlSession().selectOne("usereducation.getUserEduById", id);
		return selectOne;
	}

}
