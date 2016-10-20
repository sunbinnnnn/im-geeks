package com.imgeeks.common.service;

public interface HoyoService {
	public boolean checkFriend(int currentuserid,String username);

	public boolean addFriend(int currentuserid, String username,String roomid);
}
