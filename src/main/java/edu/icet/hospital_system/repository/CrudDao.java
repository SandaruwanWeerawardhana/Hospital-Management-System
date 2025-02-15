package edu.icet.hospital_system.repository;

import java.util.List;

public interface CrudDao<T,ID> extends SuperDao {
    boolean add(T entity);
    boolean delete(Integer id);
    boolean update(T entity);
    List<T> getAll(Integer id);
    List<T> getAllData();


}
