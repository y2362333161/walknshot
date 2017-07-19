package action;

import model.Token;
import service.AppService;

public class DeleteTokenAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;

	private AppService appService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		Token token = appService.getTokenById(id);
		appService.deleteToken(token);

		return SUCCESS;
	}

}
