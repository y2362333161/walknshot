package service.impl;

import java.math.BigInteger;
import java.util.List;

import model.Picture;
import model.Spot;
import model.Storage;
import model.Token;
import model.User;

import service.AppService;

import dao.PictureDao;
import dao.SpotDao;
import dao.StorageDao;
import dao.TokenDao;
import dao.UserDao;

public class AppServiceImpl implements AppService {

	private PictureDao pictureDao;
	private SpotDao spotDao;
	private StorageDao storageDao;
	private TokenDao tokenDao;
	private UserDao userDao;

	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	public void setSpotDao(SpotDao spotDao) {
		this.spotDao = spotDao;
	}

	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * picture
	 * 
	 */
	public Integer addPicture(Picture picture){
		return pictureDao.save(picture);
	}
	
	public void deletePicture(Picture picture){
		pictureDao.delete(picture);
	}
	
	public void updatePicture(Picture picture){
		pictureDao.update(picture);
	}
	
	public Picture getPictureById(int id){
		return pictureDao.getPictureById(id);
	}
	
	public List<Picture> getAllPictures() {
		return pictureDao.getAllPictures();
	}

	/**
	 * spot
	 * 
	 */
	public Integer addSpot(Spot spot){
		return spotDao.save(spot);
	}
	
	public void deleteSpot(Spot spot){
		spotDao.delete(spot);
	}
	
	public void updateSpot(Spot spot){
		spotDao.update(spot);
	}
	
	public Spot getSpotById(BigInteger id){
		return spotDao.getSpotById(id);
	}
	
	public List<Spot> getAllSpots() {
		return spotDao.getAllSpots();
	}
	
	/**
	 * storage
	 * 
	 */
	public Integer addStorage(Storage storage){
		return storageDao.save(storage);
	}
	
	public void deleteStorage(Storage storage){
		storageDao.delete(storage);
	}
	
	public void updateStorage(Storage storage){
		storageDao.update(storage);
	}
	
	public Storage getStorageById(int id){
		return storageDao.getStorageById(id);
	}
	
	public List<Storage> getAllStorages() {
		return storageDao.getAllStorages();
	}

	/**
	 * token
	 * 
	 */
	public Integer addToken(Token token){
		return tokenDao.save(token);
	}
	
	public void deleteToken(Token token){
		tokenDao.delete(token);
	}
	
	public void updateToken(Token token){
		tokenDao.update(token);
	}
	
	public Token getTokenById(int id){
		return tokenDao.getTokenById(id);
	}
	
	public List<Token> getAllTokens() {
		return tokenDao.getAllTokens();
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
