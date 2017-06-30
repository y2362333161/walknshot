package dao.impl;

import java.sql.Date;
import java.util.List;

import model.Latlng;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.LatlngDao;

public class LatlngDaoImpl extends HibernateDaoSupport implements LatlngDao {

	public Integer save(Latlng latlng) {
		return (Integer) getHibernateTemplate().save(latlng);
	}

	public void delete(Latlng latlng) {
		getHibernateTemplate().delete(latlng);
	}

	public void update(Latlng latlng) {
		getHibernateTemplate().merge(latlng);
	}

	public Latlng getLatlngById(int id, Date time) {
		@SuppressWarnings("unchecked")
		List<Latlng> latlngs = (List<Latlng>) getHibernateTemplate()
				.find("from Latlng as l where l.id=? and l.time=?", id, time);
		Latlng latlng = latlngs.size() > 0 ? latlngs.get(0) : null;
		return latlng;
	}

	public List<Latlng> getAllLatlngs() {
		@SuppressWarnings("unchecked")
		List<Latlng> latlngs = (List<Latlng>) getHibernateTemplate()
				.find("from Latlng");
		return latlngs;
	}

}
