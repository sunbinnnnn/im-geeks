package com.imgeeks.common.dao;

import java.util.List;

import com.imgeeks.common.bean.Label;
import com.imgeeks.utils.QueryResult;

public interface LabelDao {

	public List<Label> getLabelByUserId(int id);
	
	public QueryResult<Label> getLabelByPageNo(int Index,int pageSize );

	public List<Label> getOtherLabelByLabelList(List<Label> labellist);

	public boolean addUserlabel(int userid, int id);

	public boolean deleUserLabel(int userid, int id);

}
