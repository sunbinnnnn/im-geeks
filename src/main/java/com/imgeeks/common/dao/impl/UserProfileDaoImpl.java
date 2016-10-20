package com.imgeeks.common.dao.impl;

import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.UserProfile;
import com.imgeeks.common.dao.UserProfileDao;
@Component
public class UserProfileDaoImpl extends BaseDao  implements UserProfileDao {

	@Override
	public UserProfile getUserProfileByUserId(int id) {
		UserProfile userProfile=this.getSqlSession().selectOne("userprofile.getUserProfileByIdNoUser", id);
		return userProfile;
	}

	@Override
	public UserProfile getUserJbxxProfile(int userid) {
		UserProfile userProfile=super.getSqlSession().selectOne("userprofile.getUserJbxxProfile",userid);
		return userProfile;
	}

}
