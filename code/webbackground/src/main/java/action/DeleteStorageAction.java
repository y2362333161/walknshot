package action;

import model.Storage;
import service.AppService;

public class DeleteStorageAction extends BaseAction {

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

		Storage storage = appService.getStorageById(id);
		appService.deleteStorage(storage);

		return SUCCESS;
	}

}
