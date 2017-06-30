package action;

import java.util.List;

import model.Comment;
import service.AppService;

public class AllCommentsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		List<Comment> comments = appService.getAllComments();
		request().setAttribute("comments", comments);
		return SUCCESS;
	}
}
