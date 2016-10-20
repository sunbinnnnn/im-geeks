package com.imgeeks.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.ChatRoom;
import com.imgeeks.common.dao.ChatRoomDao;
import com.imgeeks.common.service.ChatRoomService;
@Service
public class ChatRoomServiceImpl implements ChatRoomService {
	@Autowired
	private ChatRoomDao chatRoomDao;
	@Override
	public ChatRoom getChatRoomByuid(int userid) {
		return chatRoomDao.getChatRoomByuid(userid);
	}

}
