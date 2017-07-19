package action;

import java.math.BigInteger;

import model.Spot;
import service.AppService;

public class DeleteSpotAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BigInteger id;

	private AppService appService;


	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		Spot spot = appService.getSpotById(id);
		appService.deleteSpot(spot);

		return SUCCESS;
	}

}
