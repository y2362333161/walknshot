package action;

import java.util.List;

import model.Storage;
import service.AppService;

public class AllStoragesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		List<Storage> storages = appService.getAllStorages();
		request().setAttribute("storages", storages);

		return SUCCESS;
	}
}
