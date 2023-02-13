package com.pks.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pks.sns.common.EncryptUtils;
import com.pks.sns.user.dao.UserDAO;
import com.pks.sns.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public int addUser(
			String loginId
			, String password
			, String name
			, String email
			) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
	
	public boolean duplicateId(String loginId) {
		int count = userDAO.selectCountByLoginId(loginId);
		
		if(count == 0) {
			return false;
		} else {
			return true;
		}
		
//		return count != 0;  이게 이해 안되서 위에걸로함
	}
	
	public User getUser(String loginId, String password) {
		
		String encryptPassword =  EncryptUtils.md5(password);
		
		return userDAO.selectUser(loginId, encryptPassword);
	}

}
