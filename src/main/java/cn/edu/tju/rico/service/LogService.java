package cn.edu.tju.rico.service;

import cn.edu.tju.rico.model.entity.Log;

  
/**        
 * Title: LogService.java    
 * Description: 对日志相关的业务逻辑的抽象(面向接口编程)
 * @author rico       
 * @created 2017年4月24日 上午9:28:54    
 */      
public interface LogService {
	  
	/**     
	 * @description 日志保存
	 * @author rico       
	 * @created 2017年4月24日 上午9:29:36     
	 * @param log     
	 */
	public void addLog(Log log);
}
