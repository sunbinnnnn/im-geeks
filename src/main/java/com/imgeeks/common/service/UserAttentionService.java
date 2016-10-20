package com.imgeeks.common.service;

import java.util.List;
import java.util.Map;

import com.imgeeks.common.bean.User;

public interface UserAttentionService {
	public boolean isAttentionUser(int userid,int otherid);
	
	public boolean insertAttentionUser(int userid,int otherid);
	
	public List<User> whoAttentionMe(int userid,int pageno);
	
	public Map<String,Object> getAttentNum(int userid);
	
	public List<Map<String,Object>> getUserAttention(int userid,int pageno);
	
	public List<Map<String,Object>> getAttentionUser(int userid,int pageno);
}
