package co.gmichou.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResources {

	@GET
	@Produces({"text/plain"})
	public String greet() {
		return "Hello from gmichou!";
	}
	
	@GET
	@Produces({"application/xml"})
	public String greetSXml() {
		return "<?xml version=\"1.0\" ?>\r\n"
				+ "\r\n"
				+ "<greeting>\r\n"
				+ "	<message>Hello, World</message>\r\n"
				+ "	<from>gmichou</from>\r\n"
				+ "</greeting>";
	}
	
	@GET
	@Produces({"application/json"})
	public String greetAsJson() {
		return "{\r\n"
				+ "	\"message\": \"Hello, World\",\r\n"
				+ "	\"from\": \"gmichou\"\r\n"
				+ "}";
	}
}
