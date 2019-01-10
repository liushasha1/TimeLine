package model;
/**
 * @author Shashaliu
 * @date 2018/12/23
 */
public class Dynamic {  //动态信息

	String userName;
	String content;
	String image;
	String time;
	int id;

	public Dynamic() {
		userName = null;
		content = null;
		image = null;
		time = null;
		id = 0;
	}

	public Dynamic(String un, String c, String i, String t, int id) {
		userName = un;
		content = c;
		image = i;
		time = t;
		this.id = id;
	}

	public void setUserName(String un) {
		userName = un;
	}

	public String getUserName() {
		return userName;
	}

	public void setContent(String c) {
		content = c;
	}

	public String getContent() {
		return content;
	}

	public void setImage(String i) {
		image = i;
	}

	public String getImage() {
		return image;
	}

	public void setTime(String t) {
		time = t;
	}

	public String getTime() {
		return time;
	}

	public void setId(int i) {
		id = i;
	}

	public int getId() {
		return id;
	}
}
