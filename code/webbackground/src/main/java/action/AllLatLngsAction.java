package action;

import java.util.List;

import model.Photo;
import model.Latlng;
import service.AppService;

public class AllLatLngsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		List<Latlng> latlngs = appService.getAllLatlngs();
		request().setAttribute("latlngs", latlngs);

		List<Photo> photos = appService.getAllPhotos();
		request().setAttribute("photos", photos);

		return SUCCESS;
	}
}
