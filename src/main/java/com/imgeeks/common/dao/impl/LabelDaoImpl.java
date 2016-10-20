package com.imgeeks.common.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.Label;
import com.imgeeks.common.dao.LabelDao;
import com.imgeeks.utils.QueryResult;
@Component
public  class LabelDaoImpl  extends BaseDao implements LabelDao {

	@Override
	public List<Label> getLabelByUserId(int id) {
		List<Label> selectList = this.getSqlSession().selectList("label.getLabelByUserId", id);
		return selectList;
	}

	@Override
	public QueryResult<Label> getLabelByPageNo(int Index,int pageSize) {
		
		Integer count = this.getSqlSession().selectOne("label.gettotalcount");
		QueryResult<Label> queryResult = new QueryResult<Label>(count,pageSize,Index);
		if (count == 0) {
            queryResult.setPageCount(0);
        }
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Label> list= null;
		if (count > 0) {
	            paramMap.put("startIndex", queryResult.getIndexNumber());
	            paramMap.put("pageSize", queryResult.getPageSize());
	            list = this.getSqlSession().selectList("user.queryLabelForPage", paramMap);
	            queryResult.setDatas(list);
	     }
		return queryResult;
	}

	@Override
	public List<Label> getOtherLabelByLabelList(List<Label> labellist) {
		if(labellist!=null&&labellist.size()>0){
			List list = new ArrayList();
			for (Label label : labellist) {
				list.add(label.getId());
			}
			return  this.getSqlSession().selectList("label.getOtherLabelByLabelList", list);
		}
		return this.getSqlSession().selectList("label.getAllLabels");
	}

	@Override
	public boolean addUserlabel(int userid, int id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("id", id);
		return this.getSqlSession().delete("label.addUserlabel", map)>0;
	}

	@Override
	public boolean deleUserLabel(int userid, int id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("id", id);
		return this.getSqlSession().delete("label.deleUserLabel", map)>0;
	}

}
