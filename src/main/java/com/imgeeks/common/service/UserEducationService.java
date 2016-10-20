package com.imgeeks.common.service;

import java.util.List;

import com.imgeeks.common.bean.UserEducation;

public interface UserEducationService {

	List<UserEducation> getUserEducationByUserid(int userid);

	boolean insertUserEducation(UserEducation userEducation);

	boolean deluserEdu(int id, int userid);

	boolean updatetUserEdu(UserEducation userEducation);

	UserEducation getUserEduById(int id);

}
