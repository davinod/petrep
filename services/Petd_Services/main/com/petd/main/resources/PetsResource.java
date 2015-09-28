package com.petd.main.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.petd.dao.PetDao;
import com.petd.model.Pet;
import com.petd.model.Pet.Type;

@Path("/pets")
public class PetsResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	//Return the list of pets to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Pet> getPetsBrowser(){
		List<Pet> pets = new ArrayList<Pet>();
		pets.addAll(PetDao.instance.getModel().values());
		return pets;
	}
	
	//Return the list of pets to Applications
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Pet> getPets(){
		List<Pet> pets = new ArrayList<Pet>();
		pets.addAll(PetDao.instance.getModel().values());
		return pets;
	}
	
	//Return the number of pets
	//use ../Petd_Services/pets/count
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPetsCount(){
		return String.valueOf(PetDao.instance.getModel().size());		
	}
	
	//Add a new pet
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newPet(@FormParam("id") String id,
					   @FormParam("name") String name,
					   @FormParam("type") Type type,
					   @Context HttpServletResponse servletResponse) throws IOException {
	
		//int newId = PetDao.instance.getModel().size() + 1;
		
		Pet pet = new Pet(id, name, type);
		
		PetDao.instance.getModel().put(id, pet);
		
		servletResponse.sendRedirect("file:///home/davinod/Code/eclipse_dev_ws/Petd_Services/WebContent/create_pet.html");		
	}
	
	//Define a new param to be received
	// Allows to type ../Petd_Services/pets/1
	// 1 will be treaded as parameter pet and passed to PetResource
	@Path("{pet}")
	public PetResource getPet(@PathParam("pet") String id){
		return new PetResource(uriInfo, request, id);
	}
}
