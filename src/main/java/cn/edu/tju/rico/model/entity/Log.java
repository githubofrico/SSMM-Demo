package cn.edu.tju.rico.model.entity;

import java.util.Date;

/**        
 * Title: Log.java    
 * Description: 日志实体，用于持久化
 * @author rico       
 * @created 2017年4月24日 上午9:26:42    
 */      
public class Log {

	private int id;
	private Date date;
	private String msg;

	public Log() {
		super();
	}

	public Log(Date date, String msg) {
		super();
		this.date = date;
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Log [date=" + date + ", msg=" + msg + "]";
	}

}
