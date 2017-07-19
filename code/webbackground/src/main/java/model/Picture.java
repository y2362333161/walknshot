package model;

import java.math.BigInteger;

public class Picture {

	private int id;
	private BigInteger spotid;
	private int storageid;
	
	public Picture(){
		
	}
	
	public Picture(BigInteger spotid, int storageid) {
		this.spotid = spotid;
		this.storageid = storageid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getSpotid() {
		return spotid;
	}

	public void setSpotid(BigInteger spotid) {
		this.spotid = spotid;
	}

	public int getStorageid() {
		return storageid;
	}

	public void setStorageid(int storageid) {
		this.storageid = storageid;
	}

	

}
