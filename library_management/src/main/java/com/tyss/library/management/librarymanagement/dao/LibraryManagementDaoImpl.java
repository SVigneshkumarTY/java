package com.tyss.library.management.librarymanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.tyss.library.management.librarymanagement.dto.UserInfoDto;

public class LibraryManagementDaoImpl implements LibraryManagementDao{
	
	@PersistenceUnit
	private EntityManagerFactory factory;
	
	@Override
	public void rigisterUser(UserInfoDto userInfo) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(userInfo);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
		}
		
	}//end of rigisterUser

	@Override
	public boolean loginUser(String userName, String password) {
		EntityManager em = factory.createEntityManager();
		String jpql="select from UserInfo where userEmail=:email and userPassword=:password";
		Query query=em.createQuery(jpql);
		query.setParameter("email",userName);
		query.setParameter("password",password);
		UserInfoDto userInfoDt=(UserInfoDto)query.getSingleResult();
		if(userInfoDt!=null) {
			
			return true;
		}else {
			return false;
		}	
	}//end of loginUser

	@Override
	public boolean updateUser(UserInfoDto user) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		UserInfoDto userInfo=em.find(UserInfoDto.class,user.getUserId());
		if(userInfo!=null) {
			userInfo.setUserContactNo(user.getUserContactNo());
			userInfo.setUserEmail(user.getUserEmail());
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

}
