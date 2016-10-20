package com.imgeeks.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.UserProject;
import com.imgeeks.common.dao.UserProjectDao;
import com.imgeeks.common.service.UserProjectService;
import com.imgeeks.utils.QueryResult;
@Service
public class UserProjectServiceImpl implements UserProjectService {
	@Autowired
	private UserProjectDao userProjectDao;
	
	@Override
	public boolean insertUserProject(UserProject userProject) {
		return userProjectDao.insertUserProject(userProject);
	}

	@Override
	public QueryResult<UserProject> getUserProjectList(int pageno, int workNum,int userid) {
		return userProjectDao.getUserProjectList(pageno,workNum,userid);
	}

	@Override
	public boolean delUserProject(int id) {
		return userProjectDao.delUserProject(id);
	}

}
