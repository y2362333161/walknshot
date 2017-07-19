package action;

import model.Storage;
import service.AppService;

public class UpdateStorageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private String filename;

	private AppService appService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		Storage storage = appService.getStorageById(id);
		storage.setFilename(filename);
		appService.updateStorage(storage);

		return SUCCESS;
	}

}
