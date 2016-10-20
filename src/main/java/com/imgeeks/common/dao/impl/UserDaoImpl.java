package com.imgeeks.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.User;
import com.imgeeks.common.constant.Constant;
import com.imgeeks.common.dao.UserDao;
import com.imgeeks.utils.QueryResult;

@Component
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public List<User> getUser() {

		/*
		 * 插入 User user = new User(); user.setUsername("dnc");
		 * user.setPassword("123456"); user.setAge(15); int insert =
		 * super.getSqlSession().insert("user.insertuser",user);
		 */

		// 删除 this.getSqlSession().delete("user.deletuser", 5);

		/*
		 * 跟新 User user = new User(); user.setId(1);
		 * user.setUsername("wangtao"); user.setPassword("654321");
		 * user.setAge(15); this.getSqlSession().update("user.updateuser",user
		 * );
		 */

		/*
		 * 获取总的记录数
		 * 
		 * Object selectOne =
		 * this.getSqlSession().selectOne("user.gettotalcount");
		 * System.out.println(selectOne);
		 */

		/*
		 * 分页 Map<String, Object> paramMap = new HashMap<String, Object>();
		 * Integer count = this.getSqlSession().selectOne("user.gettotalcount");
		 * 
		 * QueryResult<User> queryResult = new QueryResult<User>(count,2,1); if
		 * (count == 0) { queryResult.setPageCount(0); }
		 * 
		 * 
		 * List<User> list= null; if (count > 0) { paramMap.put("startIndex",
		 * queryResult.getIndexNumber()); paramMap.put("pageSize",
		 * queryResult.getPageSize()); list =
		 * this.getSqlSession().selectList("user.queryUserForPage", paramMap);
		 * queryResult.setDatas(list); }
		 */

		/*
		 * 模糊查询
		 * 
		 * Map<String, Object> map = new HashMap<String,Object>();
		 * map.put("username", ""); map.put("age", 15); List<User> userList =
		 * this.getSqlSession().selectList("user.getuser",map);
		 */

		return null;
	}

	@Override
	public User findUserByUserEmail(String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		User selectOne = this.getSqlSession().selectOne(
				"user.findUserByUserEmail", map);

		// UserProfile userProfile=
		// this.getSqlSession().selectOne("userprofile.getUserProfileByIdHaveUser",1);
		// System.out.println(userProfile.getUser());
		return selectOne;
	}

	@Override
	public User getUserRoleByEmail(String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		User selectOne = this.getSqlSession().selectOne(
				"user.findUserRoleByUserEmail", map);
		return selectOne;
	}

	@Override
	public User getBaseUserInformation(int id) {
		User user = this.getSqlSession().selectOne(
				"user.getBaseUserInformation", id);
		return user;
	}

	@Override
	public boolean checkUserByEmail(String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		Integer count = this.getSqlSession().selectOne("user.checkUserByEmail",
				map);
		if (count != 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean regeisterUser(String userName, String email, String md5Pwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("email", email);
		map.put("md5Pwd", md5Pwd);
		int count = super.getSqlSession().insert("user.insertuser", map);
		if (count > 0) {
			int userid = super.getSqlSession().selectOne(
					"user.getUseridByEmail", map);
			Map<String, Object> mapUseRole = new HashMap<String, Object>();
			mapUseRole.put("userid", userid);
			mapUseRole.put("roleid", 2);
			super.getSqlSession().insert("user.insertUserRole", mapUseRole);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserInformation(User user, String grjj) {

		int updateUser = super.getSqlSession().update(
				"user.updateUserInformation", user);

		Integer count = super.getSqlSession().selectOne(
				"userprofile.getCountByUserid", user.getId());
		if (count > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", user.getId());
			map.put("profile", grjj);
			super.getSqlSession().update("userprofile.updateUserProfile", map);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", user.getId());
			map.put("profile", grjj);
			super.getSqlSession().insert("userprofile.insertUserProfile", map);
		}
		return updateUser > 0 ? true : false;
	}

	@Override
	public List getUserAndSkillByUids(List<Integer> list, int userid) {
		if (list.size() > 0) {
			List<Object> selectList = super.getSqlSession().selectList(
					"user.getUserAndSkillByUids", list);
			return selectList;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);
			List randomlist = super.getSqlSession().selectList(
					"user.getUserIdByRandom", map);
			List<Object> selectList = super.getSqlSession().selectList(
					"user.getUserAndSkillByUids", randomlist);
			return selectList;
		}
	}

	@Override
	public List getRecommendByUids(List<Integer> uids) {
		if (uids.size() > 0) {
			List<Object> selectList = super.getSqlSession().selectList(
					"user.getRecommendByUids", uids);
			return selectList;
		}
		return null;
	}

	@Override
	public List getUserFriendsByUserId(int userid) {

		List<Object> selectList = super.getSqlSession().selectList(
				"user.getUserFriendsByUserId", userid);
		return selectList;
	}

	@Override
	public List<Map<String, Object>> getUserListByCase(String cityname,
			String direct, int status, int sort) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cityName", cityname);
		map.put("direct", direct);
		map.put("stus", status);
		map.put("sort", sort);
		List<Map<String, Object>> selectList = super.getSqlSession()
				.selectList("user.getUserListByCase");
		return selectList;
	}

	@Override
	public QueryResult getUserByPageno(int pageno, String username,
			String cityname) {
		if (cityname.equals("全部")) {
			cityname = "";
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);
		paramMap.put("cityname", cityname);
		Integer count = this.getSqlSession().selectOne("user.gettotalcount",
				paramMap);

		QueryResult<User> queryResult = new QueryResult<User>(count,
				Constant.Page.NUM, pageno);
		if (count == 0) {
			queryResult.setPageCount(0);
		}
		List<User> list = null;
		if (count > 0) {
			paramMap.put("startIndex", queryResult.getIndexNumber());
			paramMap.put("pageSize", queryResult.getPageSize());
			list = this.getSqlSession().selectList("user.queryUserForPage",
					paramMap);
			queryResult.setDatas(list);
		}
		return queryResult;
	}

	@Override
	public boolean deleteUserByUserId(int userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		int delete = this.getSqlSession()
				.delete("user.deleteUserByUserId", map);
		return delete > 0;
	}

	@Override
	public void updateUserHeadById(int userid, String filepath) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("filepath", filepath);
		this.getSqlSession().update("user.uploadheadimg", map);
	}
}
