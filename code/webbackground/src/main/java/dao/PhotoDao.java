package dao;

import java.util.List;

import model.Photo;

public interface PhotoDao {

	public Integer save(Photo photo);

	public void delete(Photo photo);

	public void update(Photo photo);

	public Photo getPhotoById(int id);

	public List<Photo> getAllPhotos();

}