package com.imgeeks.common.dao;

import java.util.List;

import com.imgeeks.common.bean.UserProject;
import com.imgeeks.utils.QueryResult;

public interface UserProjectDao {

	boolean insertUserProject(UserProject userProject);

	QueryResult<UserProject> getUserProjectList(int pageno, int workNum,int userid);

	boolean delUserProject(int id);

}
