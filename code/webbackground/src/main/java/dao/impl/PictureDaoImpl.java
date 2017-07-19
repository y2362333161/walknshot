package dao.impl;

import java.util.List;

import model.Picture;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.PictureDao;

public class PictureDaoImpl extends HibernateDaoSupport implements PictureDao {

	public Integer save(Picture picture) {
		return (Integer) getHibernateTemplate().save(picture);
	}

	public void delete(Picture picture) {
		getHibernateTemplate().delete(picture);
	}

	public void update(Picture picture) {
		getHibernateTemplate().merge(picture);
	}

	public Picture getPictureById(int id) {
		@SuppressWarnings("unchecked")
		List<Picture> pictures = (List<Picture>) getHibernateTemplate().find(
				"from Picture as p where p.id=?", id);
		Picture picture = pictures.size() > 0 ? pictures.get(0) : null;
		return picture;
	}
	
	public List<Picture> getAllPictures() {
		@SuppressWarnings("unchecked")
		List<Picture> pictures = (List<Picture>) getHibernateTemplate()
				.find("from Picture");
		return pictures;
	}

}
