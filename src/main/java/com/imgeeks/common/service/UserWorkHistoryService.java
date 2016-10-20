package com.imgeeks.common.service;

import java.util.List;

import com.imgeeks.common.bean.UserWorkHistory;

public interface UserWorkHistoryService {

	List<UserWorkHistory> getUserWorkHistoryByUserid(int userid);

	boolean insertWorkHhistory(UserWorkHistory userWorkHistory);

	boolean delUserWork(int id, int userid);

	boolean updatetWorkHistory(UserWorkHistory userWorkHistory);

	UserWorkHistory getUserWorkHistoryById(int id);

}
