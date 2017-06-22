package cn.edu.tju.rico.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.tju.rico.model.entity.User;
import cn.edu.tju.rico.service.UserService;

/**        
 * Title: UserQueryController  
 * Description: 处理查看指定用户ID的用户信息请求
 * @author rico       
 * @created 2017年4月24日 上午9:07:29    
 */     
@Controller
public class UserQueryController {

	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/query")
	public ModelAndView showUser(@RequestParam("id") int id){
		ModelAndView mv = new ModelAndView();
		User user = userService.getUser(id);
		if (user != null) {
			mv.addObject("user", user);
		}else{
			mv.addObject("tip", "不存在此用户...");
		}
		mv.setViewName("show");   // 设定逻辑视图
		return mv;
	}
}
