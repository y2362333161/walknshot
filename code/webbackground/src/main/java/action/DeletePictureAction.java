package action;

import model.Picture;
import service.AppService;

public class DeletePictureAction extends BaseAction {

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

		Picture picture = appService.getPictureById(id);
		appService.deletePicture(picture);

		return SUCCESS;
	}

}
