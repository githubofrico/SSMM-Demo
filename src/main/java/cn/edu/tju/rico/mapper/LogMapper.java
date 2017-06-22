package cn.edu.tju.rico.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import cn.edu.tju.rico.model.entity.Log;

  
/**        
 * Title: Log PO类与SQL的映射关系   
 * Description: 以面向对象的方式对PO类Log的操作映射成为对应的持久化操作
 * @author rico       
 * @created 2017年6月12日 下午12:09:34    
 */      
public interface LogMapper {
	  
	/**     
	 * @description 将日志持久化到数据库中
	 * @author rico       
	 * @created 2017年6月12日 下午12:15:33     
	 * @param log     
	 */
	@Insert("insert into log_inf(date,msg) values (#{date,jdbcType=TIMESTAMP},#{msg})")
	@Options(useGeneratedKeys=true, keyProperty="id")  // 自动生成主键
	public void saveLog(Log log);
}
