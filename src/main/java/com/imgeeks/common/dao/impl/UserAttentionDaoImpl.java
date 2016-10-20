package com.imgeeks.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.User;
import com.imgeeks.common.constant.Constant;
import com.imgeeks.common.dao.UserAttentionDao;
import com.imgeeks.utils.DateUtil;

@Component
public class UserAttentionDaoImpl extends BaseDao implements UserAttentionDao {

	@Override
	public boolean isAttentionUser(int userid, int otherid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("otherid", otherid);
		Integer count = this.getSqlSession().selectOne(
				"userattention.isAttentionUser", map);
		return count > 0 ? true : false;
	}

	@Override
	public boolean insertAttentionUser(int userid, int otherid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("otherid", otherid);
		map.put("createdate", DateUtil.getNowDate());
		Integer count = this.getSqlSession().insert(
				"userattention.insertAttentionUser", map);
		return count > 0 ? true : false;
	}

	@Override
	public List<User> whoAttentionMe(int userid, int pageno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("pageno", pageno * Constant.Common.PAGE_SIZE);
		map.put("size", Constant.Common.PAGE_SIZE);
		List<User> selectList = this.getSqlSession().selectList(
				"user.whoAttentionMe", map);
		return selectList;
	}

	@Override
	public Map<String, Object> getAttentNum(int userid) {
		int gzs = this.getSqlSession().selectOne("userattention.getAttentNum",
				userid);
		int bgzs = this.getSqlSession().selectOne(
				"userattention.getBAttentNum", userid);
		int dzs = this.getSqlSession().selectOne("usersupport.getSupportNum",
				userid);
		int gztotal = this.getSqlSession().selectOne(
				"userattention.getTotalAttentNum");
		int dzstotal = this.getSqlSession().selectOne(
				"usersupport.getTotalSupportNum", userid);
		Map<String, Object> map = new HashMap<String, Object>();
		int gzsprecent = gztotal == 0 ? 0 : gzs * 100 / gztotal;
		int bgzsprecent = gztotal == 0 ? 0 : bgzs * 100 / gztotal;
		int dzprecent = dzstotal == 0 ? 0 : dzs * 100 / dzstotal;
		map.put("gzs", gzs);
		map.put("bgzs", bgzs);
		map.put("dzs", dzs);
		map.put("gzsprecent", gzsprecent);
		map.put("bgzsprecent", bgzsprecent);
		map.put("dzprecent", dzprecent);
		return map;
	}

	@Override
	public List<Map<String, Object>> getUserAttention(int userid, int pageno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("pageno", pageno * (Constant.Common.PAGE_SIZE - 1));
		map.put("size", Constant.Common.PAGE_SIZE - 1);
		List<Map<String, Object>> selectList = this.getSqlSession().selectList(
				"user.getUserAttention", map);
		return selectList;
	}

	@Override
	public List<Map<String, Object>> getAttentionUser(int userid, int pageno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("pageno", pageno * (Constant.Common.PAGE_SIZE - 1));
		map.put("size", Constant.Common.PAGE_SIZE - 1);
		List<Map<String, Object>> selectList = this.getSqlSession().selectList(
				"user.getAttentionUser", map);
		return selectList;
	}

}
