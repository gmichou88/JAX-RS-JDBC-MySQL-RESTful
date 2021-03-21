package co.gmichou.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import co.gmichou.dao.ContactsDao;
import co.gmichou.dao.DaoException;
import co.gmichou.dao.DaoFactory;
import co.gmichou.entity.Contact;

@Path("/contacts")
public class ContactsResource {

	private ContactsDao dao;
	
	public ContactsResource() throws DaoException {
		dao = DaoFactory.getContactsDao();
	}
	
	@GET
	@Produces({"application/json"})
	public Response index() throws DaoException {
		
		return Response.ok(dao.findAll()).build();
	}
	
	@Path("/{id}")
	@Produces({"application/json"})
	@GET
	public Response show(@PathParam("id") Integer id) throws DaoException {
		return Response.ok(dao.findById(id)).build();
	}
	
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response create(Contact contact) throws DaoException {
		contact = dao.addContact(contact);
		return Response.ok(contact).build();
	}
	
	@Path("/{id}")
	@PUT
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response update(@PathParam("id") Integer id, Contact contact) throws DaoException {
		contact.setId(id);
		contact = dao.updateContact(contact);
		return Response.ok(contact).build();
	}
	
	@Path("/{id}")
	@DELETE
	@Produces({"application/json"})
	public Response destroy(@PathParam("id") Integer id) throws DaoException {
		dao.deleteContact(id);
		return Response.ok().build();
	}
	
	
	
}
