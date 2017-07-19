package action;

import model.Token;
import service.AppService;

public class UpdateTokenAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private int uid;
	private String passphrase;

	private AppService appService;

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

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		Token token = appService.getTokenById(id);
		token.setUid(uid);
		token.setPassphrase(passphrase);
		appService.updateToken(token);

		return SUCCESS;
	}

}
