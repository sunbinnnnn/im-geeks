package com.imgeeks.common.dao;

import java.util.List;
import java.util.Map;

import com.imgeeks.common.bean.User;

public interface UserSupportDao {
	public boolean isSupportUser(int userid,int otherid);
	
	public boolean insertSupportUser(int userid,int otherid);

	public List<Map> whoSupportMe(int userid, int pageno);
}
