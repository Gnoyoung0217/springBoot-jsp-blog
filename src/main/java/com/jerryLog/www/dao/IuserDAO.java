package com.jerryLog.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jerryLog.www.bean.UserBean;

@Repository
@Mapper
public interface IuserDAO {
	
	public List<UserBean> getUserList();
	public UserBean getUserInfo(UserBean userInfo);
	public int putUserSignUp(UserBean userInfo);
}
