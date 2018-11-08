package cn.edu.zju.gis.po;

public class LogsVo {
	private int id;
	private int userid;
	private String username;
	private String authority;
	private String logintime;
	private String loginip;
	
	private int limit;//分页大小
	private int offset;//页码

	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	private String loginaddress;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthority() {
		return authority;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getLoginaddress() {
		return loginaddress;
	}
	public void setLoginaddress(String loginaddress) {
		this.loginaddress = loginaddress;
	}
	
}
