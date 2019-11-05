package com.tyss.library.management.librarymanagement.dao;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.tyss.library.management.librarymanagement.dto.BookInfoDto;
import com.tyss.library.management.librarymanagement.dto.StudentBookDto;
import com.tyss.library.management.librarymanagement.dto.UserInfoDto;
@Repository
public class LibraryManagementDaoImpl implements LibraryManagementDao{
	
	@PersistenceUnit
	private EntityManagerFactory factory;
	
	@Autowired
	private JavaMailSender sender;
	
	@Override
	public void registerUser(UserInfoDto userInfo,String to,String subject,String body){
		
		MimeMessage message=sender.createMimeMessage();
		
		
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			
			MimeMessageHelper helper =new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			sender.send(message);
			et.begin();
			em.persist(userInfo);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
		}
		
	}//end of registerUser

	@Override
	public UserInfoDto loginUser(String userName, String password) {
		EntityManager em = factory.createEntityManager();
		String jpql="from UserInfoDto where userEmail=:email and userPassword=:password";
		try {
			Query query=em.createQuery(jpql);
			query.setParameter("email",userName);
			query.setParameter("password",password);
			return (UserInfoDto)query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
		
	}//end of loginUser

	@Override
	public boolean updateUser(UserInfoDto user) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		UserInfoDto userInfo=em.find(UserInfoDto.class,user.getUserId());
		if(userInfo!=null) {
			et.begin();
            userInfo.setUserName(user.getUserName());
            userInfo.setUserEmail(user.getUserEmail());
            userInfo.setUserPassword(user.getUserPassword());
            userInfo.setUserContactNo(user.getUserContactNo());
            userInfo.setUserRole(user.getUserRole());
            userInfo.setUserGender(user.getUserGender());
            et.commit();
			return true;
		}
		
		return false;
	}//End of updateUser

	@Override
	public boolean removeUser(int id) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		UserInfoDto userInfo = em.find(UserInfoDto.class, id);

		if (userInfo == null) {
			return false;
		} else {

			try {
				em.remove(userInfo);
				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
			}
			return true;
		}
		
	}// End of removeUser

	@Override
	public void addBook(BookInfoDto bookDto) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(bookDto);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
		}
			
	}//End of addBook

	@Override
	public boolean deleteBook(int id) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		BookInfoDto bookInfo = em.find(BookInfoDto.class, id);

		if (bookInfo == null) {
			return false;
		} else {

			try {
				em.remove(bookInfo);
				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
			}
			return true;
		}
		
	}//End of deleteBook

	@Override
	public boolean updateBook(BookInfoDto book) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		BookInfoDto bookInfo=em.find(BookInfoDto.class,book.getBookId());
		if(bookInfo!=null) {
			et.begin();
			bookInfo.setBookName(book.getBookName());
			bookInfo.setAuthorName(book.getAuthorName());
			bookInfo.setCategory(book.getCategory());
			et.commit();
			return true;
		}
		return false;
	}//End of updateBook

	@Override
	public BookInfoDto getBook(BookInfoDto bookDto) {
		EntityManager em = factory.createEntityManager();

		BookInfoDto bookInfo = em.find(BookInfoDto.class, bookDto.getBookId());
		if (bookInfo == null) {
			return null;
		}
		return bookInfo;
	}//End of getBook

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfoDto> getAllUsers() {
		EntityManager em = factory.createEntityManager();
		String queryStr="from UserInfoDto";
		Query query=em.createQuery(queryStr);
		return query.getResultList();
	}//End of getAllUsers

	@SuppressWarnings("unchecked")
	@Override
	public List<BookInfoDto> getAllBooks() {
		EntityManager em = factory.createEntityManager();
		String queryStr="from BookInfoDto";
		Query query=em.createQuery(queryStr);
		return query.getResultList();
	}

	@Override
	public StudentBookDto acceptBookRequest(int userId, int bookId) {
		StudentBookDto studentBook=new StudentBookDto();
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			BookInfoDto bookInfoDto=em.find(BookInfoDto.class,bookId);
			et.begin();
			if(bookInfoDto!=null) {
				Date date = new Date();
				studentBook.setUserId(userId);
				studentBook.setBookId(bookId);
				studentBook.setBookName(bookInfoDto.getBookName());
				studentBook.setAuthorName(bookInfoDto.getAuthorName());
				studentBook.setCategory(bookInfoDto.getCategory());
				studentBook.setIssueDate(date);
				
				em.remove(bookInfoDto);
				
				em.persist(studentBook);
			}
			
			et.commit();	
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
		}
		return studentBook;
	}//End of acceptBookRequest

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentBookDto> getIssueBookList(int userId) {
		EntityManager em = factory.createEntityManager();
		String jpql="from StudentBookDto where userId=:userId";
		Query query=em.createQuery(jpql);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public boolean returnBook(int bookId) {
		boolean flag=false;
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			StudentBookDto studentBook=em.find(StudentBookDto.class,bookId);
			BookInfoDto bookInfo=new BookInfoDto();
			if(studentBook!=null) {
				bookInfo.setBookId(studentBook.getBookId());
				bookInfo.setBookName(studentBook.getBookName());
				bookInfo.setAuthorName(studentBook.getAuthorName());
				bookInfo.setCategory(studentBook.getCategory());
				
				et.begin();
				em.remove(studentBook);
				em.persist(bookInfo);
				et.commit();
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<UserInfoDto> searchByName(String userName) {
		EntityManager em = factory.createEntityManager();
		String jpql="from UserInfoDto where userName=:userName";
		Query query=em.createQuery(jpql);
		query.setParameter("userName",userName);
		return query.getResultList();
	}

	@Override
	public boolean changePassword(int id, String password,String newPassword) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		String jpql="from UserInfoDto where userId=:id and userPassword=:password";
		try {
			Query query=em.createQuery(jpql);
			query.setParameter("id",id);
			query.setParameter("password",password);
		 UserInfoDto dto = (UserInfoDto)query.getSingleResult();
		 if(dto != null) {
			 dto.setUserPassword(newPassword);
			 et.commit();
			 return true;
		 }else {
			 return false;
		 }
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
}
