package com.imgeeks.common.dao.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.imgeeks.base.BaseDao;
import com.imgeeks.common.bean.ChatRoom;
import com.imgeeks.common.dao.ChatRoomDao;
@Component
public class ChatRoomDaoImpl extends BaseDao implements ChatRoomDao {

	@Override
	public ChatRoom getChatRoomByuid(int userid) {
		ChatRoom selectOne = this.getSqlSession().selectOne("chat.getChatRoomByuid",userid);
		return selectOne;
	}

}
