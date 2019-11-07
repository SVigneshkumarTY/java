package com.tyss.library.management.librarymanagement.dto;


import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LibraryResponse {
	private int statusCode;
	private String message;
	private String description;
	private List<Users> users;
	private List<BooksInventory> books;
	private List<BookRegistration> bookReg;
	private List<BookTransaction> bookTrans;
	public List<BookRegistration> getBookReg() {
		return bookReg;
	}
	public void setBookReg(List<BookRegistration> bookReg) {
		this.bookReg = bookReg;
	}
	public List<BookTransaction> getBookTrans() {
		return bookTrans;
	}
	public void setBookTrans(List<BookTransaction> bookTrans) {
		this.bookTrans = bookTrans;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	public List<BooksInventory> getBooks() {
		return books;
	}
	public void setBooks(List<BooksInventory> books) {
		this.books = books;
	}
	
		
}
