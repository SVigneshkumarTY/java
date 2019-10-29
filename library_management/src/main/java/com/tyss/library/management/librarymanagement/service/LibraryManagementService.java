package com.tyss.library.management.librarymanagement.service;

import java.util.List;

import com.tyss.library.management.librarymanagement.dto.BookInfoDto;
import com.tyss.library.management.librarymanagement.dto.StudentBookDto;
import com.tyss.library.management.librarymanagement.dto.UserInfoDto;

public interface LibraryManagementService {
	public void registerUser(UserInfoDto userInfo);
	public UserInfoDto loginUser(String userName,String password);
	public boolean updateUser(UserInfoDto userInfo);
	public boolean removeUser(int id);
	public List<UserInfoDto> getAllUsers();
	public void addBook(BookInfoDto bookDto);
	public boolean deleteBook(int id);
	public boolean updateBook(BookInfoDto bookDto);
	public BookInfoDto getBook(BookInfoDto bookDto);
	public List<BookInfoDto> getAllBooks();
	
	public StudentBookDto acceptBookRequest(int userId,int bookId);
	public List<StudentBookDto> getIssueBookList(int userId);
	public boolean returnBook(int bookId);
}
