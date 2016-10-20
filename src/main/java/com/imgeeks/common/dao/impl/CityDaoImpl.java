package com.imgeeks.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.City;
import com.imgeeks.common.dao.CityDao;
@Component
public class CityDaoImpl extends BaseDao implements CityDao {

	@Override
	public List getAllCity() {
		List<City> selectList = super.getSqlSession().selectList("city.getAllCity");
		return selectList;
	}

}
