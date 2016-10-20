package com.imgeeks.common.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.SessionUser;
import com.imgeeks.common.bean.User;
import com.imgeeks.common.bean.UserSkill;
import com.imgeeks.common.dao.UserDao;
import com.imgeeks.common.dao.UserSkillDao;
import com.imgeeks.common.service.UserService;
import com.imgeeks.utils.QueryResult;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserSkillDao userSkillDao;
	
	@Override
	public List<User> getUser() {
		return userDao.getUser();
	}
	
	
	@Override
	public List<SessionUser> findSessionUserByUserName(String userName,
			int roleid) {
		return null;
	}
	
	
	@Override
	public User getUserByEmail(String email) {
		return userDao.findUserByUserEmail(email);
	}


	@Override
	public User getUserRoleByEmail(String email) {
		return userDao.getUserRoleByEmail(email);
	}


	@Override
	public User getBaseUserInformation(int id) {
		return userDao.getBaseUserInformation(id);
	}


	@Override
	public boolean checkUserByEmail(String email) {
		return userDao.checkUserByEmail(email);
	}


	@Override
	public boolean regeisterUser(String userName, String email, String md5Pwd) {
		return userDao.regeisterUser(userName, email, md5Pwd);
	}


	@Override
	public boolean updateUserInformation(User user, String grjj) {
		return userDao.updateUserInformation(user,grjj);
	}


	@Override
	public List<Integer> getUserIdByKilll(List<UserSkill> userKillByUsers) {
		List<Integer> list = new ArrayList<Integer>();
		for (UserSkill userSkill : userKillByUsers) {
			Integer id =userSkillDao.getUserIdByKill(userSkill);
			if(id>0){
				list.add(id);
			}
		}
		if(list.size()>4){
			List<Integer> userid = new ArrayList<Integer>();
			boolean flag = true;
			while (flag) {
				int randomUserid =(int) (Math.random()*(list.size()));
				if(!userid.contains(list.get(randomUserid))){
					userid.add(list.get(randomUserid));
				}
				if(userid.size()==4){
					flag = false;
				}
			}
			return userid;
		}else{
			return list;
		}
	}


	@Override
	public List getUserAndSkillByUids(List<Integer> uids,int userid) {
		return userDao.getUserAndSkillByUids(uids,userid);
	}


	@Override
	public List getRecommendByUids(List<Integer> uids) {
		return userDao.getRecommendByUids(uids);
	}


	@Override
	public List getUserFriendsByUserId(int userid) {
		return userDao.getUserFriendsByUserId(userid);
	}


	@Override
	public List<Map<String, Object>> getUserListByCase(String cityname,
			String direct, int status, int sort) {
		return userDao.getUserListByCase(cityname,direct,status,sort);
	}


	@Override
	public QueryResult getUserByPageno(int pageno,String username,String cityname) {
		return userDao.getUserByPageno(pageno, username, cityname);
	}


	@Override
	public boolean deleteUserByUserId(int userid) {
		return userDao.deleteUserByUserId(userid);
	}


	@Override
	public void updateUserHeadById(int userid, String filepath) {
		userDao.updateUserHeadById(userid,filepath);
	}

}
