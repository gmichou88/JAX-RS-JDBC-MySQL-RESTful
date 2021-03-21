package co.gmichou.dao;

import java.util.List;

import co.gmichou.entity.Contact;

public interface ContactsDao {

	// CRUD OPERATIONS
	public Contact addContact(Contact contact) throws DaoException;

	public Contact findById(Integer id) throws DaoException;

	public Contact updateContact(Contact contact) throws DaoException;

	public void deleteContact(Integer id) throws DaoException;

	// QUERIES

	public List<Contact> findAll() throws DaoException;

	public List<Contact> findByCity(String city) throws DaoException;

	public List<Contact> findByCountry(String country) throws DaoException;
}
