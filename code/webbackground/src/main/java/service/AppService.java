package service;

import java.util.List;

import model.Comment;
import model.Path;
import model.Latlng;
import model.Photo;
import model.User;

public interface AppService {

	/**
	 * comment
	 * 
	 */
	public List<Comment> getAllComments();

	/**
	 * path
	 * 
	 */
	public List<Path> getAllPaths();

	/**
	 * latlng
	 * 
	 */
	public List<Latlng> getAllLatlngs();

	/**
	 * photo
	 * 
	 */
	public List<Photo> getAllPhotos();
	
	/**
	 * user
	 * 
	 */
	public Integer addUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);

	public User getUserById(int id);
	
	public List<User> getAllUsers();

}
