package cn.edu.tju.rico.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.tju.rico.dto.UserDTO;
import cn.edu.tju.rico.service.UserService;
  
/**        
 * Title:  RegistController   
 * Description: 处理用户的注册请求
 * @author rico       
 * @created 2017年6月12日 上午11:57:42    
 */      
@Controller
public class RegistController {
	
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(UserDTO userDto){  // HandlerAdaptor将form表单中的各属性映射到userDto对象中
		System.out.println(userDto);
		boolean flag = userService.regist(userDto);
		return flag ? "login" : "regist";    // 根据注册结果，转向特定页面
	}
}
