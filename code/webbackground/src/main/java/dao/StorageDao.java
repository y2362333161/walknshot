package dao;

import java.util.List;

import model.Storage;

public interface StorageDao {

	public Integer save(Storage storage);

	public void delete(Storage storage);

	public void update(Storage storage);

	public Storage getStorageById(int id);

	public List<Storage> getAllStorages();

}