package cn.edu.tju.rico.aspect;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.edu.tju.rico.mapper.LogMapper;
import cn.edu.tju.rico.model.entity.Log;

/**
 * Title: LogAspect.java 
 * Description: 在控制台打印日志的切面类
 * 
 * @author rico
 * @created 2017年4月24日 上午9:10:52
 */
@Component
// 由Spring来创建Aspect类
@Aspect
// 声明该类是一个切面
public class LogAspect {

	private LogMapper logMapper;

	public LogMapper getLogMapper() {
		return logMapper;
	}

	@Resource
	public void setLogMapper(LogMapper logMapper) {
		this.logMapper = logMapper;
	}

	// Around Advice:可以修改目标方法的参数及返回值
	@Around("execution(* cn.edu.tju.rico.service.impl.UserServiceImpl.login(..))")
	public Object loginLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("用户开始登录...");

		// 获取目标方法的参数
		Object[] args = jp.getArgs();

		// 获取目标方法的返回值
		Boolean rvt = (Boolean) jp.proceed(args);

		// 打印系统执行信息： 目标对象+目标方法
		System.out.println("系统正在执行方法： " + jp.getTarget().getClass().getName()
				+ "." + jp.getSignature().getName());

		// 打印系统执行信息： 目标对象+目标方法
		System.out.println("传入方法的参数为： " + Arrays.toString(args));

		if (rvt.booleanValue() == true) {
			logMapper
					.saveLog(new Log(new Date(), "用户 " + args[0] + " 登录成功..."));
		} else {
			logMapper
					.saveLog(new Log(new Date(), "用户 " + args[0] + " 登录失败..."));
		}

		System.out.println("用户登录完毕... ");

		return rvt; // 将目标方法的返回值不做任何处理便返回
	}

	@Around("execution(* cn.edu.tju.rico.service.impl.UserServiceImpl.regist(..))")
	public Object registLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("用户开始注册...");

		// 获取目标方法的参数
		Object[] args = jp.getArgs();

		// 获取目标方法的返回值
		Boolean rvt = (Boolean) jp.proceed(args);

		// 打印系统执行信息： 目标对象+目标方法
		System.out.println("系统正在执行方法： " + jp.getTarget().getClass().getName()
				+ "." + jp.getSignature().getName());

		// 打印系统执行信息： 目标对象+目标方法
		System.out.println("传入方法的参数为： " + Arrays.toString(args));

		if (rvt.booleanValue() == true) {
			logMapper
					.saveLog(new Log(new Date(), "用户 " + args[0] + " 注册成功..."));
		} else {
			logMapper
					.saveLog(new Log(new Date(), "用户 " + args[0] + " 注册失败..."));
		}

		System.out.println("用户注册完毕...");

		return rvt; // 将目标方法的返回值不做任何处理便返回
	}

	@Around("execution(* cn.edu.tju.rico.service.impl.UserServiceImpl.getUser(..))")
	public Object selectUserLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("开始查询指定用户信息...");

		// 获取目标方法的参数
		Object[] args = jp.getArgs();

		// 获取目标方法的返回值
		Object rvt = jp.proceed(args);

		// 打印系统执行信息： 目标对象+目标方法
		System.out.println("系统正在执行方法： " + jp.getTarget().getClass().getName()
				+ "." + jp.getSignature().getName());

		// 打印系统执行信息： 目标对象+目标方法
		System.out.println("传入方法的参数为： " + Arrays.toString(args));

		if (rvt != null) {
			logMapper.saveLog(new Log(new Date(), "存在ID为 " + args[0]
					+ " 的用户，查询成功..."));
		} else {
			logMapper.saveLog(new Log(new Date(), "不存在ID为 " + args[0]
					+ " 的用户，查询失败..."));
		}

		System.out.println("查询指定用户信息完毕... ");

		return rvt; // 将目标方法的返回值不做任何处理便返回
	}
}
