package action;

import model.Spot;
import service.AppService;

public class AddSpotAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int uid;
	private double latitude;
	private double longitude;

	private AppService appService;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {
		
		Spot spot = new Spot(uid,latitude,longitude);
		appService.addSpot(spot);

		return SUCCESS;
	}

}
