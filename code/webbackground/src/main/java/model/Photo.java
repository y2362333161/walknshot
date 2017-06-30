package model;

import java.sql.Date;

public class Photo {

	private int id;
	private int pathid;
	private Date time;
	private String filename;
	private boolean isPrivate;
	private int views;

	public Photo() {
	}

	public Photo(int id, int pathid, Date time, String filename, boolean isPrivate, int views) {
		this.id = id;
		this.pathid = pathid;
		this.time = time;
		this.filename = filename;
		this.isPrivate = isPrivate;
		this.views = views;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPathid() {
		return pathid;
	}

	public void setPathid(int pathid) {
		this.pathid = pathid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
	
}
