package action;

import java.util.List;

import model.Picture;
import service.AppService;

public class AllPicturesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		List<Picture> pictures = appService.getAllPictures();
		request().setAttribute("pictures", pictures);

		return SUCCESS;
	}
}
