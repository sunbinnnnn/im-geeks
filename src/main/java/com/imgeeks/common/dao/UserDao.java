package com.imgeeks.common.dao;

import java.util.List;
import java.util.Map;

import com.imgeeks.common.bean.User;
import com.imgeeks.utils.QueryResult;

public interface UserDao {
	public List<User> getUser();


	public User findUserByUserEmail(String email);

	public User getUserRoleByEmail(String email);

	public User getBaseUserInformation(int id);


	public boolean checkUserByEmail(String email);


	public boolean regeisterUser(String userName, String email, String md5Pwd);


	public boolean updateUserInformation(User user, String grjj);


	public List getUserAndSkillByUids(List<Integer> uids,int userid);


	public List getRecommendByUids(List<Integer> uids);


	public List getUserFriendsByUserId(int userid);


	public List<Map<String, Object>> getUserListByCase(String cityname,
			String direct, int status, int sort);


	public QueryResult getUserByPageno(int pageno,String username,String cityname);


	public boolean deleteUserByUserId(int userid);


	public void updateUserHeadById(int userid, String filepath);
}
