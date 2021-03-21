package co.gmichou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.gmichou.entity.Contact;
import co.gmichou.utils.DbUtil;

public class JdbcContactsDao implements ContactsDao {

	@Override
	public Contact addContact(Contact contact) throws DaoException {
		String sql = "insert into contacts(name, gender, email, phone, city, country) values(?, ?, ?, ?, ?, ?)";
		try (
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		){
				stmt.setString(1, contact.getName());
				stmt.setString(2, contact.getGender());
				stmt.setString(3, contact.getEmail());
				stmt.setString(4, contact.getPhone());
				stmt.setString(5, contact.getCity());
				stmt.setString(6, contact.getCountry());
				
				stmt.executeUpdate();
				ResultSet keys = stmt.getGeneratedKeys();
				
				keys.next();
				contact.setId(keys.getInt(1));
				
				return contact;
		}
		catch(Exception e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public Contact findById(Integer id) throws DaoException {
		
		String sql = "select * from contacts where id=?";
		
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				Contact contact = toContact(result);
				
				result.close();
				
				return contact;
			}
			
			result.close();
		}
		catch(Exception e){
			throw new DaoException(e);
		}
		
		return null;
	}

	private Contact toContact(ResultSet result) throws SQLException {
		Contact contact = new Contact();
		contact.setId(result.getInt("id"));
		contact.setName(result.getString("name"));
		contact.setGender(result.getString("gender"));
		contact.setEmail(result.getString("email"));
		contact.setPhone(result.getString("phone"));
		contact.setCity(result.getString("city"));
		contact.setCountry(result.getString("country"));
		return contact;
	}

	@Override
	public Contact updateContact(Contact contact) throws DaoException {
		String sql = "update contacts set name=?, gender=?, email=?, phone=?, city=?, country=? where id=?";
		
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setString(3, contact.getEmail());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getCity());
			stmt.setString(6, contact.getCountry());
			stmt.setInt(7, contact.getId());
			
			int count = stmt.executeUpdate();
			if(count == 0) {
				throw new DaoException("No records updated; invalid id supplied: " + contact.getId());
			}
		}
		catch(Exception e) {
			throw new DaoException(e);
		}
		
		return contact;
	}

	@Override
	public void deleteContact(Integer id) throws DaoException {
		String sql = "delete from contacts where id=?";
		
		try(
				Connection connection = DbUtil.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
		){
			stmt.setInt(1, id);
			
			int count = stmt.executeUpdate();
			if(count == 0) {
				throw new DaoException("No records deleted; invalid id supplied: " + id);
			}
		}
		catch(Exception e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public List<Contact> findAll() throws DaoException {
		String sql = "select * from contacts";
		List<Contact> list = new ArrayList<>();
		
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet result = stmt.executeQuery();
		){
			while(result.next()) {
				Contact contact = toContact(result);
				list.add(contact);
			}
			
		}
		catch(Exception e) {
			throw new DaoException(e);
		}
		
		return list;
	}

	@Override
	public List<Contact> findByCity(String city) throws DaoException {

		String sql = "select * from contacts where city=?";
		List<Contact> list = new ArrayList<>();
		
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
		){
			stmt.setString(1, city);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				Contact contact = toContact(result);
				list.add(contact);
			}
			result.close();
		}
		catch(Exception e) {
			throw new DaoException(e);
		}
		
		return list;
		
	}

	@Override
	public List<Contact> findByCountry(String country) throws DaoException {
		String sql = "select * from contacts where country=?";
		List<Contact> list = new ArrayList<>();
		
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
		){
			stmt.setString(1, country);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				Contact contact = toContact(result);
				list.add(contact);
			}
			result.close();
		}
		catch(Exception e) {
			throw new DaoException(e);
		}
		
		return list;
	}

}
