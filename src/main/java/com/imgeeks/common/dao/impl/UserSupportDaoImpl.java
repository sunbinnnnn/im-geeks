package com.imgeeks.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.User;
import com.imgeeks.common.constant.Constant;
import com.imgeeks.common.dao.UserSupportDao;
import com.imgeeks.utils.DateUtil;
@Component
public class UserSupportDaoImpl extends BaseDao implements UserSupportDao {

	@Override
	public boolean isSupportUser(int userid, int otherid) {
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("otherid", otherid);
        Integer count = this.getSqlSession().selectOne("usersupport.isSupportUser",map);
		return count>0?true:false;
	}

	@Override
	public boolean insertSupportUser(int userid, int otherid) {
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("otherid", otherid);
		map.put("createdate", DateUtil.getNowDate());
        Integer count = this.getSqlSession().insert("usersupport.insertSupportUser", map);
		return count>0?true:false;
	}

	@Override
	public List<Map> whoSupportMe(int userid, int pageno) {
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("pageno", pageno*Constant.Common.PAGE_SIZE);
		map.put("size", Constant.Common.PAGE_SIZE);
		List<Map> selectList = this.getSqlSession().selectList("user.whoSupportMe", map);
		return selectList;
	}


}
