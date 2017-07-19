package dao;

import java.math.BigInteger;
import java.util.List;

import model.Spot;

public interface SpotDao {

	public Integer save(Spot spot);

	public void delete(Spot spot);

	public void update(Spot spot);

	public Spot getSpotById(BigInteger id);

	public List<Spot> getAllSpots();

}