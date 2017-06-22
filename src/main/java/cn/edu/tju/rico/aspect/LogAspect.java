package cn.edu.tju.rico.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

  
/**        
 * Title: LogAspect.java    
 * Description: 在控制台打印日志的切面类
 * @author rico       
 * @created 2017年4月24日 上午9:10:52    
 */      
@Component  // 由Spring来创建Aspect类
@Aspect		// 声明该类是一个切面
public class LogAspect {

	// Around Advice:可以修改目标方法的参数及返回值
	@Around("execution(* cn.edu.tju.rico.service.impl.UserServiceImpl.*(..))")
	public Object printLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("开始记录日志...");
		
		// 获取目标方法的参数
		Object[] args = jp.getArgs();
		
		// 获取目标方法的返回值
		Object rvt = jp.proceed(args);
		
		//打印系统执行信息： 目标对象+目标方法
		System.out.println("系统正在执行方法： " + jp.getTarget().getClass().getName()
				+ "." + jp.getSignature().getName());
		
		//打印系统执行信息： 目标对象+目标方法
		System.out.println("传入方法的参数为： " + Arrays.toString(args));
		
		System.out.println("日志记录完毕... ");
		
		return rvt;   // 将目标方法的返回值不做任何处理便返回
	}
}
