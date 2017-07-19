package model;


public class Token {

	private int id;
	private int uid;
	private String passphrase;
	
	public Token(){
		
	}

	public Token(int uid, String passphrase) {
		this.uid = uid;
		this.passphrase = passphrase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

}
