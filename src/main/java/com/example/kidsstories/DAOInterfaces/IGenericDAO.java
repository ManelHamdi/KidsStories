package com.example.kidsstories.DAOInterfaces;

import java.util.List;

public interface IGenericDAO<E, PK extends java.io.Serializable> {

	public PK save(E newInstance);
	public boolean saveBool(E newInstance);
	public boolean update(E transientObject);
	public void saveOrUpdate(E transientObject);
	public void delete(E persistentObject);
	public List<E> findAll();
	public List<E> findByCriteria(String propertyName1, Object value1);
	public E findById(String id);
//	public Object findBy(Class<?> clazz, Serializable id);


}
