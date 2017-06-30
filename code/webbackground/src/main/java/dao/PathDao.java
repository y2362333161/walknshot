package dao;

import java.util.List;

import model.Path;

public interface PathDao {

	public Integer save(Path order);

	public void delete(Path order);

	public void update(Path order);

	public Path getPathById(int id);

	public List<Path> getAllPaths();

}