package service;

import java.math.BigInteger;
import java.util.List;

import model.Picture;
import model.Spot;
import model.Storage;
import model.Token;
import model.User;

public interface AppService {

	/**
	 * picture
	 * 
	 */
	public Integer addPicture(Picture picture);

	public void deletePicture(Picture picture);

	public void updatePicture(Picture picture);

	public Picture getPictureById(int id);

	public List<Picture> getAllPictures();
	
	/**
	 * spot
	 * 
	 */
	public Integer addSpot(Spot spot);

	public void deleteSpot(Spot spot);

	public void updateSpot(Spot spot);

	public Spot getSpotById(BigInteger id);

	public List<Spot> getAllSpots();
	
	/**
	 * storage
	 * 
	 */
	public Integer addStorage(Storage storage);

	public void deleteStorage(Storage storage);

	public void updateStorage(Storage storage);

	public Storage getStorageById(int id);

	public List<Storage> getAllStorages();
	
	/**
	 * token
	 * 
	 */
	public Integer addToken(Token token);

	public void deleteToken(Token token);

	public void updateToken(Token token);

	public Token getTokenById(int id);

	public List<Token> getAllTokens();
	
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
