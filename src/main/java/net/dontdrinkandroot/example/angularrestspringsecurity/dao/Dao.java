package net.dontdrinkandroot.example.angularrestspringsecurity.dao;

import java.util.List;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.BaseEntity;


public interface Dao<T extends BaseEntity, I>
{

	List<T> findAll();


	T find(I id);


	T save(T newsEntry);


	void delete(I id);

}