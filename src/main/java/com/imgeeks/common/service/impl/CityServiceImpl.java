package com.imgeeks.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.dao.CityDao;
import com.imgeeks.common.service.CityService;
@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;
	@Override
	public List getAllCity() {
		return cityDao.getAllCity();
	}

}
