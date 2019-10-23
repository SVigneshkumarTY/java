package com.tyss.library.management.librarymanagement.dao;

import com.tyss.library.management.librarymanagement.dto.UserInfoDto;

public interface LibraryManagementDao {
	public void rigisterUser(UserInfoDto userInfo);
	public boolean loginUser(String userName,String password);
	public boolean updateUser(UserInfoDto userInfo);
	public boolean removeUser(int id);
}
