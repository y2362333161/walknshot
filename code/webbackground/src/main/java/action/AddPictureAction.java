package action;

import java.math.BigInteger;

import model.Picture;
import service.AppService;

public class AddPictureAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BigInteger spotid;
	private int storageid;

	private AppService appService;

	public BigInteger getSpotid() {
		return spotid;
	}

	public void setSpotid(BigInteger spotid) {
		this.spotid = spotid;
	}

	public int getStorageid() {
		return storageid;
	}

	public void setStorageid(int storageid) {
		this.storageid = storageid;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		Picture picture = new Picture(spotid, storageid);
		appService.addPicture(picture);

		return SUCCESS;
	}

}
