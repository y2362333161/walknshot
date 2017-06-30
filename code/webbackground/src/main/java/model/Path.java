package model;

import java.util.HashSet;
import java.util.Set;

public class Path {

	private int id;
	private int userid;
	private boolean isPublic;
	private Set<Latlng> Latlngs = new HashSet<Latlng>();

	public Path() {
	}

	public Path(int id, int userid, boolean isPublic, Set<Latlng> Latlngs) {
		this.id = id;
		this.userid = userid;
		this.isPublic = isPublic;
		this.Latlngs = Latlngs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Set<Latlng> getLatlngs() {
		return Latlngs;
	}

	public void setLatlngs(Set<Latlng> Latlngs) {
		this.Latlngs = Latlngs;
	}
	
}
