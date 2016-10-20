package com.imgeeks.common.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.dao.HoyoDao;
import com.imgeeks.utils.DateUtil;
@Component
public class HoyoDaoImpl extends BaseDao implements HoyoDao {

	@Override
	public boolean checkFriend(int currentuserid, String username) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		int userNameid = super.getSqlSession().selectOne("user.getUserIdByUserName",map);
		Map<String,Object> map1= new HashMap<String ,Object>();
		map1.put("currentuserid", currentuserid);
		map1.put("userNameid", userNameid);
		int count = super.getSqlSession().selectOne("hoyo.checkFriend",map1);
		return count>0?true:false;
	}

	@Override
	public boolean addFriend(int currentuserid, String username,String roomid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		int userNameid = super.getSqlSession().selectOne("user.getUserIdByUserName",map);
		Map<String,Object> map1= new HashMap<String ,Object>();
		map1.put("currentuserid", currentuserid);
		map1.put("userNameid", userNameid);
		map1.put("createdate",DateUtil.getNowDate() );
		map1.put("roomid", roomid);
		int insert = super.getSqlSession().insert("hoyo.addFriend", map1);
		return insert>0?true:false;
	}

}
