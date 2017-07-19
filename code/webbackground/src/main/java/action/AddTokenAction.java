package action;

import model.Token;
import service.AppService;

public class AddTokenAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int uid;
	private String passphrase;

	private AppService appService;


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

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {
		
		Token token = new Token(uid,passphrase);
		appService.addToken(token);

		return SUCCESS;
	}

}
