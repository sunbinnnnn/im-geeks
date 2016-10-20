package com.imgeeks.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.User;
import com.imgeeks.common.dao.UserAttentionDao;
import com.imgeeks.common.service.UserAttentionService;
@Service
public class UserAttentionServiceImpl implements UserAttentionService {
	@Autowired
	private UserAttentionDao userAttentionDao;
	@Override
	public boolean isAttentionUser(int userid,int otherid) {
		return userAttentionDao.isAttentionUser(userid,otherid);
	}
	@Override
	public boolean insertAttentionUser(int userid, int otherid) {
		return userAttentionDao.insertAttentionUser(userid,otherid);
	}
	@Override
	public List<User> whoAttentionMe(int userid,int pageno) {
		return userAttentionDao.whoAttentionMe(userid,pageno);
	}
	@Override
	public Map<String, Object> getAttentNum(int userid) {
		return userAttentionDao.getAttentNum(userid);
	}

	@Override
	public List<Map<String,Object>> getUserAttention(int userid,int pageno){
		return userAttentionDao.getUserAttention(userid,pageno);
	}
	@Override
	public List<Map<String,Object>> getAttentionUser(int userid,int pageno){
		return userAttentionDao.getAttentionUser(userid,pageno);
	}

}
