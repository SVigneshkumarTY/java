package com.tyss.library.management.librarymanagement.service;

import java.util.List;

import com.tyss.library.management.librarymanagement.dto.BookRegistration;
import com.tyss.library.management.librarymanagement.dto.BookTransaction;
import com.tyss.library.management.librarymanagement.dto.BooksInventory;
import com.tyss.library.management.librarymanagement.dto.Users;

public interface LibraryManagementService {
	public boolean registerUser(Users userInfo,String to,String subject,String body);
	public Users loginUser(String userName,String password);
	public boolean updateUser(Users userInfo);
	public boolean removeUser(int id);
	public List<Users> getAllUsers();
	public boolean addBook(BooksInventory bookDto);
	public boolean deleteBook(int id);
	public boolean updateBook(BooksInventory bookDto);
	public BooksInventory getBook(BooksInventory bookDto);
	public List<BooksInventory> getAllBooks();
	
	/*
	 * public StudentBookDto acceptBookRequest(int userId,int bookId); public
	 * List<StudentBookDto> getIssueBookList(int userId); public boolean
	 * returnBook(int bookId);
	 */
	public List<Users> searchByName(String userName);
	public boolean changePassword(String email,String password,String newPassword);
	public boolean requestBook(BooksInventory book,int id);
	public List<BookRegistration> getAllBook();
	public boolean removeBook(int bId);
	
	public boolean allocateBook(BookRegistration request);

	public List<BookTransaction> getAlluserBooks(int id);
	
	public boolean removeBookReg(int bId);
	public boolean addBookAgain(BookTransaction book);
	public boolean addBookAgain1(BookRegistration book);
}
