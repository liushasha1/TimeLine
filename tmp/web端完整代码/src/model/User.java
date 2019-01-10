package model;
/**
 * @author Shashaliu
 * @date 2018/12/23
 */
public class User {

	private String teleNum;
	private String gender;
	private String name;
	private String password;

	public User() {
		teleNum = null;
		gender = null;
		name = null;
		password = null;
	}

	public User(String t, String g, String n, String p) {
		teleNum = t;
		gender = g;
		name = n;
		password = p;
	}

	public User(String t, String p) {
		teleNum = t;
		password = p;
		gender = null;
		name = null;
	}

	public void setTeleNum(String t) {
		teleNum = t;
	}

	public String getTeleNum() {
		return teleNum;
	}

	public void setGender(String g) {
		gender = g;
	}

	public String getGender() {
		return gender;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String p) {
		password = p;
	}

	public String getPassword() {
		return password;
	}
}
