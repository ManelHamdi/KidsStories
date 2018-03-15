package com.example.kidsstories.DAOInterfaces;

import java.util.List;

public interface IGenericDAO<E, PK extends java.io.Serializable> {

    PK save(E newInstance);

    boolean saveBool(E newInstance);

    boolean update(E transientObject);

    void saveOrUpdate(E transientObject);

    void delete(E persistentObject);

    List<E> findAll();

    List<E> findByCriteria(String propertyName1, Object value1);

    E findById(String id);
//	public Object findBy(Class<?> clazz, Serializable id);


}
