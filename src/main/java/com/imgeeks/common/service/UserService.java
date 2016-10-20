package com.imgeeks.common.service;

import java.util.List;
import java.util.Map;

import com.imgeeks.common.bean.SessionUser;
import com.imgeeks.common.bean.User;
import com.imgeeks.common.bean.UserSkill;
import com.imgeeks.utils.QueryResult;

public interface UserService {
	
	public List<User> getUser();

	public List<SessionUser> findSessionUserByUserName(String userName,
			int roleid);

	public User getUserByEmail(String email);
	
	
	public User getUserRoleByEmail(String email);
	
	public User getBaseUserInformation(int id);
	
	public boolean checkUserByEmail(String email);

	public boolean regeisterUser(String userName, String email, String md5Pwd);

	public boolean updateUserInformation(User user,  String grjj);

	public List<Integer> getUserIdByKilll(List<UserSkill> userKillByUsers);

	public List getUserAndSkillByUids(List<Integer> uids,int userid);

	public List getRecommendByUids(List<Integer> uids);

	public List getUserFriendsByUserId(int userid);

	public List<Map<String, Object>> getUserListByCase(String cityname,
			String direct, int status, int sort);

	public QueryResult getUserByPageno(int pageno,String username,String cityname);

	public boolean deleteUserByUserId(int userid);

	public void updateUserHeadById(int userid, String filepath);
}
