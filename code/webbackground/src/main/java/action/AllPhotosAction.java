package action;

import java.util.List;

import model.Photo;
import service.AppService;

public class AllPhotosAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		List<Photo> photos = appService.getAllPhotos();
		request().setAttribute("photos", photos);
		
		return SUCCESS;
	}
}
