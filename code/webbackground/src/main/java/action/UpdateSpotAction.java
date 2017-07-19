package action;

import java.math.BigInteger;

import model.Spot;
import service.AppService;

public class UpdateSpotAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BigInteger id;
	private int uid;
	private double latitude;
	private double longitude;

	private AppService appService;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

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

		Spot spot = appService.getSpotById(id);
		spot.setUid(uid);
		spot.setLatitude(latitude);
		spot.setLongitude(longitude);
		appService.updateSpot(spot);

		return SUCCESS;
	}

}
