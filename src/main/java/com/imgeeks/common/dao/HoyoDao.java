package com.imgeeks.common.dao;

public interface HoyoDao {

	boolean checkFriend(int currentuserid, String username);

	boolean addFriend(int currentuserid, String username,String roomid);

}
