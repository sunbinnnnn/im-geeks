package com.imgeeks.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.Label;
import com.imgeeks.common.dao.LabelDao;
import com.imgeeks.common.service.LabelService;
import com.imgeeks.utils.QueryResult;
@Service
public class LabelServiceImpl implements LabelService {
	@Autowired
	private LabelDao labelDao;

	@Override
	public List<Label> getLabelByUserId(int id) {
		return labelDao.getLabelByUserId(id);
	}

	@Override
	public QueryResult<Label> getLabelByPageNo(int Index, int pageSize) {
		return labelDao.getLabelByPageNo(Index, pageSize);
	}

	@Override
	public List<Label> getOtherLabelByLabelList(List<Label> labellist) {
		return labelDao.getOtherLabelByLabelList( labellist);
	}

	@Override
	public boolean addUserlabel(int userid, int id) {
		return labelDao.addUserlabel(userid,id);
	}

	@Override
	public boolean deleUserLabel(int userid, int id) {
		return labelDao.deleUserLabel(userid,id);
	}
}
