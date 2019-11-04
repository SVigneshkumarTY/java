package com.tyss.library.management.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.library.management.librarymanagement.dto.BookInfoDto;
import com.tyss.library.management.librarymanagement.dto.StudentBookDto;
import com.tyss.library.management.librarymanagement.dto.UserInfoDto;
import com.tyss.library.management.librarymanagement.service.LibraryManagementService;
@CrossOrigin(origins="*",allowedHeaders="*",allowCredentials="true")
@RestController
@RequestMapping("librarymanagement")
public class LibraryManagementController {
	@Autowired
	private LibraryManagementService service;
	
	@PostMapping(path="/register",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void registerUser(@RequestBody UserInfoDto userInfo) {
		service.registerUser(userInfo);
	}
	
	@GetMapping(path="/login",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserInfoDto loginUser(@RequestParam("userName") String name,@RequestParam("password") String password){
		System.out.println("username..."+name);
		System.out.println("password...."+password);
		UserInfoDto user=service.loginUser(name, password);
		System.out.println("--------------------------");
		System.out.println("Username..:"+user.getUserName());
		System.out.println("UserPasword..:"+user.getUserPassword());
		System.out.println("UserEmail..:"+user.getUserEmail());
		return user;
	}
	
	@PutMapping(path="/update",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean updateUser(@RequestBody UserInfoDto userInfo) {
		return service.updateUser(userInfo);
	}
	
	@DeleteMapping(path="/remove/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteUser(@PathVariable("id") int id) {
		return service.removeUser(id);
	}
	
	@GetMapping(path="/get-all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserInfoDto> getAllUsers(){
		return service.getAllUsers();
	}
	
	@PostMapping(path="/addBook",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addBook(@RequestBody BookInfoDto bookDto) {
		service.addBook(bookDto);
	}
	
	@PutMapping(path="/updateBook",consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean updateBook(@RequestBody BookInfoDto bookInfo) {
		return service.updateBook(bookInfo);
	}
	
	@DeleteMapping(path="/deleteBook/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteBook(@PathVariable("id") int id) {
		return service.deleteBook(id);
	}
	
	@GetMapping(path="/getBook",produces=MediaType.APPLICATION_JSON_VALUE)
	public BookInfoDto getBook(BookInfoDto book) {
		return service.getBook(book);
	}
	
	@GetMapping(path="/getAllBook",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<BookInfoDto> getAllBooks(){
		return service.getAllBooks();
	}//End of getAllBooks
	
	@GetMapping(path="/acceptBookRequest",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public StudentBookDto acceptBookRequest(@RequestParam("userId") int userId,@RequestParam("bookId") int bookId) {
		return service.acceptBookRequest(userId, bookId);
	}
	
	@GetMapping(path="/getIssueBooks",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StudentBookDto> getIssueBooks(@RequestParam("userId") int userId) {
		return service.getIssueBookList(userId);
	}
	
	@DeleteMapping(path="/returnBook",produces=MediaType.APPLICATION_JSON_VALUE)
    public boolean returnIssueBook(@RequestParam("bookId") int bookId) {
    	return service.returnBook(bookId);
    }
	
	@GetMapping(path="/getByName",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserInfoDto> searchByName(@RequestParam("userName") String userName){
		List<UserInfoDto> users=service.searchByName(userName.trim());
		return users;
	}
	
	@GetMapping(path="/changepwd",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean changePassword(@RequestParam("userId") int id,@RequestParam("userPassword") String oldPassword,@RequestParam("newPassword") String newPassword) {
		
		return service.changePassword(id, oldPassword, newPassword);
		
	}
}
