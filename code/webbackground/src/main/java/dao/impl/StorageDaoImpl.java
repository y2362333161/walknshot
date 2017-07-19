package dao.impl;

import java.util.List;

import model.Storage;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.StorageDao;

public class StorageDaoImpl extends HibernateDaoSupport implements StorageDao {

	public Integer save(Storage storage) {
		return (Integer) getHibernateTemplate().save(storage);
	}

	public void delete(Storage storage) {
		getHibernateTemplate().delete(storage);
	}

	public void update(Storage storage) {
		getHibernateTemplate().merge(storage);
	}

	public Storage getStorageById(int id) {
		@SuppressWarnings("unchecked")
		List<Storage> storages = (List<Storage>) getHibernateTemplate().find(
				"from Storage as st where st.id=?", id);
		Storage storage = storages.size() > 0 ? storages.get(0) : null;
		return storage;
	}
	
	public List<Storage> getAllStorages() {
		@SuppressWarnings("unchecked")
		List<Storage> storages = (List<Storage>) getHibernateTemplate()
				.find("from Storage");
		return storages;
	}

}
