package action;

import model.User;
import service.AppService;

public class UpdateUserAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;

	private AppService appService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		User user = appService.getUserById(id);
		user.setUsername(username);
		user.setPassword(password);
		appService.updateUser(user);

		return SUCCESS;
	}

}
