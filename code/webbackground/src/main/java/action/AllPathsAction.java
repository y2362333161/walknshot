package action;

import java.util.List;

import model.Latlng;
import model.Path;
import service.AppService;

public class AllPathsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		List<Path> paths = appService.getAllPaths();
		request().setAttribute("paths", paths);

		List<Latlng> latlngs = appService.getAllLatlngs();
		request().setAttribute("latlngs", latlngs);

		return SUCCESS;
	}
}
