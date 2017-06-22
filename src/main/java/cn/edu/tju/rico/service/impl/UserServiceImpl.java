package cn.edu.tju.rico.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.tju.rico.dto.UserDTO;
import cn.edu.tju.rico.mapper.LogMapper;
import cn.edu.tju.rico.mapper.UserMapper;
import cn.edu.tju.rico.model.entity.Log;
import cn.edu.tju.rico.model.entity.User;
import cn.edu.tju.rico.service.UserService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Component("userService")
public class UserServiceImpl implements UserService{
	
	private UserMapper userMapper;
	private LogMapper logMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Resource  // 默认按名称(userMapper)注入,若名称匹配失败，则按照类型匹配
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public LogMapper getLogMapper() {
		return logMapper;
	}

	@Resource
	public void setLogMapper(LogMapper logMapper) {
		this.logMapper = logMapper;
	}

	/** 
	 * @description 登录逻辑的具体实现，对应事务包括用户的查询和日志的插入两部分
	 * @author rico       
	 * @created 2017年4月24日 上午9:34:07      
	 * @param uname
	 * @param passwd
	 * @return 用户名、密码匹配成功，返回true；否则，返回false;    
	 * @see cn.edu.tju.rico.service.UserService#login(java.lang.String, java.lang.String)     
	 */  
	@Transactional(readOnly = false)
	public boolean login(String uname, String passwd) {
		// TODO Auto-generated method stub
		User user = userMapper.findUserByUnameAndPasswd(uname, passwd);
		
		Log log = new Log();
		log.setDate(new Date());
		if (user == null) {
			log.setMsg(uname + "登录失败...");
		}else{
			log.setMsg(uname + "登录成功...");
		}
		logMapper.saveLog(log);
		return user == null ? false : true;
	}

	  
	/** 
	 * @description 用户注册逻辑的具体实现
	 * @author rico       
	 * @created 2017年6月12日 下午12:21:45      
	 * @param userDto
	 * @return     
	 * @see cn.edu.tju.rico.service.UserService#regist(cn.edu.tju.rico.dto.UserDTO)     
	 */  
	@Transactional(readOnly = false)
	public boolean regist(UserDTO userDto) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Log log = new Log();
		log.setDate(new Date());
		if (!userDto.getPasswd().equals(userDto.getPasswd1())) {
			log.setMsg("用户 " + userDto.getUname() + " 在注册时，两次密码输入不一致...");
		} else if (userMapper.findUserByUname(userDto.getUname()) != null) {
			log.setMsg("用户 " + userDto.getUname() + " 在注册时，该用户名已被占用...");
		} else {
			User user = new User(userDto);
			userMapper.saveUser(user);
			log.setMsg("用户 " + userDto.getUname() + " 注册成功...");
			flag = true;
		}
		logMapper.saveLog(log);
		return flag;
	}

	  
	/** 
	 * @description 用户获取逻辑的具体实现
	 * @author rico       
	 * @created 2017年6月12日 下午12:22:00      
	 * @param id
	 * @return     
	 * @see cn.edu.tju.rico.service.UserService#getUser(int)     
	 */  
	    
	@Transactional(readOnly = true)
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return userMapper.findUserById(id);
	}
}
