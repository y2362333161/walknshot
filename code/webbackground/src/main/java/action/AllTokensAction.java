package action;

import java.util.List;

import model.Token;
import service.AppService;

public class AllTokensAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		List<Token> tokens = appService.getAllTokens();
		request().setAttribute("tokens", tokens);

		return SUCCESS;
	}
}
