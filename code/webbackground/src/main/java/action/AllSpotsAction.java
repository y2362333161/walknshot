package action;

import java.util.List;

import model.Spot;
import service.AppService;

public class AllSpotsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		List<Spot> spots = appService.getAllSpots();
		request().setAttribute("spots", spots);

		return SUCCESS;
	}
}
