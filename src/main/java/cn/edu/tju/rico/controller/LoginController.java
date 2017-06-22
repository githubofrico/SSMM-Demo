package cn.edu.tju.rico.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.edu.tju.rico.service.UserService;

/**        
 * Title: LoginController    
 * Description: 处理登录请求
 * @author rico       
 * @created 2017年6月12日 下午12:05:46    
 */      
@Controller
@SessionAttributes("uname")  // 将uname放到HttpSession当中
public class LoginController {

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(@RequestParam("uname") String uname,
			@RequestParam("passwd") String passwd, Model model) {
		boolean flag = userService.login(uname, passwd);
		if (flag) {      // 若登录验证成功，则将uname放入HttpSession中,并转到"欢迎页"
			model.addAttribute("uname", uname);
			return "welcome";   // 逻辑视图，转向欢迎页面
		}
		return "login";   // 逻辑视图，依然转向登录页面
	}
}
