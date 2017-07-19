package model;

import java.math.BigInteger;
import java.util.Date;


public class Spot {

	private BigInteger id;
	private int uid;
	private double latitude;
	private double longitude;
	private Date time;
	
	public Spot(){
		
	}

	public Spot(int uid, double latitude, double longitude) {
		this.uid = uid;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
