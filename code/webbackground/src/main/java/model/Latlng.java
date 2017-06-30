package model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import java.io.Serializable;

public class Latlng implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date time;
	private float latitude;
	private float longitude;
	private Set<Photo> photos = new HashSet<Photo>();
	
	public Latlng() {
	}

	public Latlng(int id, Date time, float latitude, float longitude, Set<Photo> photos) {
		this.id = id;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.photos = photos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Latlng other = (Latlng) obj;
		if (id != other.id)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	
}
