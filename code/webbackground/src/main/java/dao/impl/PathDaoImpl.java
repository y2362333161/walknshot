package dao.impl;

import java.util.List;

import model.Path;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.PathDao;

public class PathDaoImpl extends HibernateDaoSupport implements PathDao {

	public Integer save(Path path) {
		return (Integer) getHibernateTemplate().save(path);
	}

	public void delete(Path path) {
		getHibernateTemplate().delete(path);
	}

	public void update(Path path) {
		getHibernateTemplate().merge(path);
	}

	public Path getPathById(int id) {
		@SuppressWarnings("unchecked")
		List<Path> paths = (List<Path>) getHibernateTemplate().find(
				"from Path as p where p.id=?", id);
		Path path = paths.size() > 0 ? paths.get(0) : null;
		return path;
	}

	public List<Path> getAllPaths() {
		@SuppressWarnings("unchecked")
		List<Path> paths = (List<Path>) getHibernateTemplate().find(
				"from Path");
		return paths;
	}

}
