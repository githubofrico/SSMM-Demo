package cn.edu.tju.rico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**        
 * Title:  ChoiceController   
 * Description: 处理去应用首页、去登录和去注册三个请求
 * @author rico       
 * @created 2017年6月12日 下午12:02:14    
 */      
@Controller
@RequestMapping("/choice")
public class ChoiceController {
	
	@RequestMapping("/choose")
	public String choose() {
		return "index";  // 逻辑视图，返回首页
	}

	@RequestMapping("/tologin")
	public String goLogin() {
		return "login";   // 逻辑视图，登录页面
	}
	
	@RequestMapping("/toregist")
	public String goRegist() {
		return "regist";    // 逻辑视图，注册页面
	}
}
