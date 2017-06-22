package cn.edu.tju.rico.dto;

/**        
 * Title: UserDTO.java    
 * Description: DTO(Data Transfer Object),用于传值，
 * 与domain/entity/po/bean不同的是DTO不作持久化(包含确认密码的User)
 * @author rico       
 * @created 2017年4月24日 上午9:24:16    
 */      
public class UserDTO {
	
	private String uname;
	private String passwd;
	private String passwd1;
	private String gentle;
	private String email;
	private String city;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getGentle() {
		return gentle;
	}

	public void setGentle(String gentle) {
		this.gentle = gentle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPasswd1() {
		return passwd1;
	}

	public void setPasswd1(String passwd1) {
		this.passwd1 = passwd1;
	}

	@Override
	public String toString() {
		return "UserDTO [uname=" + uname + ", passwd=" + passwd + ", passwd1="
				+ passwd1 + ", gentle=" + gentle + ", email=" + email
				+ ", city=" + city + "]";
	}
}
