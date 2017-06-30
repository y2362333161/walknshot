package service.impl;

import java.util.List;

import model.Comment;
import model.Path;
import model.Latlng;
import model.Photo;
import model.User;
import service.AppService;
import dao.CommentDao;
import dao.PathDao;
import dao.LatlngDao;
import dao.PhotoDao;
import dao.UserDao;

public class AppServiceImpl implements AppService {

	private CommentDao commentDao;
	private PathDao pathDao;
	private LatlngDao latlngDao;
	private PhotoDao photoDao;
	private UserDao userDao;

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public void setPathDao(PathDao pathDao) {
		this.pathDao = pathDao;
	}

	public void setLatlngDao(LatlngDao latlngDao) {
		this.latlngDao = latlngDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	/**
	 * comment
	 * 
	 */
	public List<Comment> getAllComments() {
		return commentDao.getAllComments();
	}

	/**
	 * path
	 * 
	 */
	public List<Path> getAllPaths() {
		return pathDao.getAllPaths();
	}

	/**
	 * latlng
	 * 
	 */
	public List<Latlng> getAllLatlngs() {
		return latlngDao.getAllLatlngs();
	}
	
	/**
	 * Shopping cart
	 * 
	 */
	public List<Photo> getAllPhotos() {
		return photoDao.getAllPhotos();
	}

	/**
	 * user
	 * 
	 */
	public Integer addUser(User user) {
		return userDao.save(user);
	}

	public void deleteUser(User user) {
		userDao.delete(user);
	}

	public void updateUser(User user) {
		userDao.update(user);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

}
