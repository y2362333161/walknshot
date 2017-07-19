package action;

import model.Storage;
import service.AppService;

public class AddStorageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private AppService appService;


	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {
		
		Storage storage = new Storage(filename);
		appService.addStorage(storage);

		return SUCCESS;
	}

}
