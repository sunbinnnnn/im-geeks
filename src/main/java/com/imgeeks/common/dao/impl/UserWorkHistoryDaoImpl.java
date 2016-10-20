package com.imgeeks.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.UserWorkHistory;
import com.imgeeks.common.dao.UserWorkHistoryDao;
@Component
public class UserWorkHistoryDaoImpl extends BaseDao  implements UserWorkHistoryDao {

	@Override
	public List<UserWorkHistory> getUserWorkHistoryByUserid(int userid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		List<UserWorkHistory>  UserWorkHistory =super.getSqlSession().selectList("userworkhistory.getUserWorkHistoryByUserid", map);
		return UserWorkHistory;
	}

	@Override
	public boolean insertWorkHhistory(UserWorkHistory userWorkHistory) {
		int insert = super.getSqlSession().insert("userworkhistory.insertUserWorkhistory", userWorkHistory);
		return insert>0?true:false;
	}

	@Override
	public boolean delUserWork(int id, int userid) {
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("id", id);
		map.put("userid", userid);
		int delete = super.getSqlSession().delete("userworkhistory.delUserWorkhistory", map);
		return delete>0?true:false;
	}

	@Override
	public boolean updatetWorkHistory(UserWorkHistory userWorkHistory) {
		int update = super.getSqlSession().update("userworkhistory.updateUserWorkhistory", userWorkHistory);
		return update>0?true:false;
	}

	@Override
	public UserWorkHistory getUserWorkHistoryById(int id) {
		UserWorkHistory userWorkHistory = super.getSqlSession().selectOne("userworkhistory.getUserWorkHistoryById", id);
		return userWorkHistory;
	}

}
