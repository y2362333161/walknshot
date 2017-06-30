package dao.impl;

import java.util.List;

import model.Photo;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.PhotoDao;

public class PhotoDaoImpl extends HibernateDaoSupport implements PhotoDao {

	public Integer save(Photo photo) {
		return (Integer) getHibernateTemplate().save(photo);
	}

	public void delete(Photo photo) {
		getHibernateTemplate().delete(photo);
	}

	public void update(Photo photo) {
		getHibernateTemplate().merge(photo);
	}

	public Photo getPhotoById(int id) {
		@SuppressWarnings("unchecked")
		List<Photo> photos = (List<Photo>) getHibernateTemplate()
				.find("from Photo as p where p.id=?", id);
		Photo photo = photos.size() > 0 ? photos.get(0) : null;
		return photo;
	}

	public List<Photo> getAllPhotos() {
		@SuppressWarnings("unchecked")
		List<Photo> photos = (List<Photo>) getHibernateTemplate()
				.find("from Photo");
		return photos;
	}

}
