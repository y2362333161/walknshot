package action;

import java.math.BigInteger;

import model.Picture;
import service.AppService;

public class UpdatePictureAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	int id;
	private BigInteger spotid;
	private int storageid;

	private AppService appService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

		Picture picture = appService.getPictureById(id);
		picture.setSpotid(spotid);
		picture.setStorageid(storageid);
		appService.updatePicture(picture);

		return SUCCESS;
	}

}
