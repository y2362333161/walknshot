package dao;

import java.sql.Date;
import java.util.List;

import model.Latlng;

public interface LatlngDao {

	public Integer save(Latlng latlng);

	public void delete(Latlng latlng);

	public void update(Latlng latlng);

	public Latlng getLatlngById(int id, Date time);

	public List<Latlng> getAllLatlngs();

}