package com.imgeeks.common.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imgeeks.utils.DateUtil;
import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.UserProject;
import com.imgeeks.common.dao.UserProjectDao;
import com.imgeeks.utils.QueryResult;
@Component
public class UserProjectDaoImpl extends BaseDao  implements UserProjectDao {

	@Override
	public boolean insertUserProject(UserProject userProject) {
		userProject.setDate(DateUtil.getNowDate());
		userProject.setStatu("0");
		int insert = super.getSqlSession().insert("userproject.insertUserProject", userProject);
		return insert>0?true:false;
	}

	@Override
	public QueryResult<UserProject> getUserProjectList(int pageno, int workNum,int userid) {
		    Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userid",userid);
	        Integer count = this.getSqlSession().selectOne("userproject.gettotalcount",paramMap);

			QueryResult<UserProject> queryResult = new QueryResult<UserProject>(count,workNum,pageno);
			if (count == 0) {
	            queryResult.setPageCount(0);
	        }
			
			
			List<UserProject> list= null;
			if (count > 0) {
		            paramMap.put("startIndex", queryResult.getIndexNumber());
		            paramMap.put("pageSize", queryResult.getPageSize());
		            list = this.getSqlSession().selectList("userproject.queryUserForPage", paramMap);
		            queryResult.setDatas(list);
		
			}
			return queryResult;
	}

	@Override
	public boolean delUserProject(int id) {
		int delete = super.getSqlSession().delete("userproject.delUserProject", id);
		return delete>0?true:false;
	}
}
