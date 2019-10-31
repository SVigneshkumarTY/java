package com.tyss.library.management.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.library.management.librarymanagement.dao.LibraryManagementDao;
import com.tyss.library.management.librarymanagement.dto.BookInfoDto;
import com.tyss.library.management.librarymanagement.dto.StudentBookDto;
import com.tyss.library.management.librarymanagement.dto.UserInfoDto;
@Service
public class LibraryManagementServiceImpl implements LibraryManagementService{
	@Autowired
    private LibraryManagementDao dao;
	@Override
	public void registerUser(UserInfoDto userInfo) {
		dao.registerUser(userInfo);
	}

	@Override
	public UserInfoDto loginUser(String userName, String password) {
		return dao.loginUser(userName, password);
	}

	@Override
	public boolean updateUser(UserInfoDto userInfo) {
		return dao.updateUser(userInfo);
	}

	@Override
	public boolean removeUser(int id) {
		return dao.removeUser(id);
	}

	@Override
	public void addBook(BookInfoDto bookDto) {
		dao.addBook(bookDto);	
	}

	@Override
	public boolean deleteBook(int id) {
		return dao.deleteBook(id);
	}

	@Override
	public boolean updateBook(BookInfoDto bookDto) {
		return dao.updateBook(bookDto);
	}

	@Override
	public BookInfoDto getBook(BookInfoDto bookDto) {
		return dao.getBook(bookDto);
	}

	@Override
	public List<UserInfoDto> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public List<BookInfoDto> getAllBooks() {
		return dao.getAllBooks();
	}

	@Override
	public StudentBookDto acceptBookRequest(int userId, int bookId) {
		return dao.acceptBookRequest(userId, bookId);
	}

	@Override
	public List<StudentBookDto> getIssueBookList(int userId) {
		return dao.getIssueBookList(userId);
	}

	@Override
	public boolean returnBook(int bookId) {
		return dao.returnBook(bookId);
	}

	@Override
	public List<UserInfoDto> searchByName(String userName) {
		return dao.searchByName(userName);
	}
}
