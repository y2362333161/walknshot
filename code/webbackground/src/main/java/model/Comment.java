package model;

import java.sql.Date;

public class Comment {

	private int id;
	private int photoid;
	private int userid;
	private String content;
	private Date comtime;

	public Comment() {
	}

	public Comment(int photoid, int userid, String content, Date comtime) {
		this.photoid = photoid;
		this.userid = userid;
		this.content = content;
		this.comtime = comtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getComtime() {
		return comtime;
	}

	public void setComtime(Date comtime) {
		this.comtime = comtime;
	}

}
