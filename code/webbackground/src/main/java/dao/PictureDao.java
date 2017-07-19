package dao;

import java.util.List;

import model.Picture;

public interface PictureDao {

	public Integer save(Picture picture);

	public void delete(Picture picture);

	public void update(Picture picture);

	public Picture getPictureById(int id);

	public List<Picture> getAllPictures();

}