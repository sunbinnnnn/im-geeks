package com.imgeeks.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.dao.HobbyDao;
@Component
public class HobbyDaoImpl extends BaseDao implements HobbyDao {

	@Override
	public List getAllHobby() {
		return super.getSqlSession().selectList("hobby.getAllHobby");
	}

}
