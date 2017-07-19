package dao.impl;

import java.math.BigInteger;
import java.util.List;

import model.Spot;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.SpotDao;

public class SpotDaoImpl extends HibernateDaoSupport implements SpotDao {

	public Integer save(Spot spot) {
		return (Integer) getHibernateTemplate().save(spot);
	}

	public void delete(Spot spot) {
		getHibernateTemplate().delete(spot);
	}

	public void update(Spot spot) {
		getHibernateTemplate().merge(spot);
	}

	public Spot getSpotById(BigInteger id) {
		@SuppressWarnings("unchecked")
		List<Spot> spots = (List<Spot>) getHibernateTemplate().find(
				"from Spot as sp where sp.id=?", id);
		Spot spot = spots.size() > 0 ? spots.get(0) : null;
		return spot;
	}
	
	public List<Spot> getAllSpots() {
		@SuppressWarnings("unchecked")
		List<Spot> spots = (List<Spot>) getHibernateTemplate()
				.find("from Spot");
		return spots;
	}

}
