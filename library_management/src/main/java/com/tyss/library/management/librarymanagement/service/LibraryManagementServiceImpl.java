package com.tyss.library.management.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.library.management.librarymanagement.dao.LibraryManagementDao;
import com.tyss.library.management.librarymanagement.dto.BookRegistration;
import com.tyss.library.management.librarymanagement.dto.BookTransaction;
import com.tyss.library.management.librarymanagement.dto.BooksInventory;
import com.tyss.library.management.librarymanagement.dto.Users;
@Service
public class LibraryManagementServiceImpl implements LibraryManagementService{
	@Autowired
    private LibraryManagementDao dao;
	@Override
	public boolean registerUser(Users userInfo,String to,String subject,String body) {
		return dao.registerUser(userInfo,to,subject,body);
	}

	@Override
	public Users loginUser(String userName, String password) {
		return dao.loginUser(userName, password);
	}

	@Override
	public boolean updateUser(Users userInfo) {
		return dao.updateUser(userInfo);
	}

	@Override
	public boolean removeUser(int id) {
		return dao.removeUser(id);
	}

	@Override
	public boolean addBook(BooksInventory bookDto) {
		return dao.addBook(bookDto);	
	}

	@Override
	public boolean deleteBook(int id) {
		return dao.deleteBook(id);
	}

	@Override
	public boolean updateBook(BooksInventory bookDto) {
		return dao.updateBook(bookDto);
	}

	@Override
	public BooksInventory getBook(BooksInventory bookDto) {
		return dao.getBook(bookDto);
	}

	@Override
	public List<Users> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public List<BooksInventory> getAllBooks() {
		return dao.getAllBooks();
	}
	/*
	 * @Override public StudentBookDto acceptBookRequest(int userId, int bookId) {
	 * return dao.acceptBookRequest(userId, bookId); }
	 * 
	 * @Override public List<StudentBookDto> getIssueBookList(int userId) { return
	 * dao.getIssueBookList(userId); }
	 * 
	 * @Override public boolean returnBook(int bookId) { return
	 * dao.returnBook(bookId); }
	 */

	@Override
	public List<Users> searchByName(String userName) {
		return dao.searchByName(userName);
	}

	@Override
	public boolean changePassword(String email, String password, String newPassword) {
		return dao.changePassword(email, password, newPassword);
	}

	@Override
	public boolean requestBook(BooksInventory book,int id) {
		return dao.requestBook(book,id);
	}

	@Override
	public List<BookRegistration> getAllBook() {
		return dao.getAllBook();
	}

	@Override
	public boolean removeBook(int bId) {
		return dao.removeBook(bId);
	}

	@Override
	public boolean addBook(BookRegistration bookAction) {
		return dao.addBook(bookAction);
	}

	@Override
	public List<BookTransaction> getAlluserBooks(int id) {
		return dao.getAlluserBooks(id);
	}

	@Override
	public boolean removeBookReg(String bId) {
		return dao.removeBookReg(bId);
	}
}
