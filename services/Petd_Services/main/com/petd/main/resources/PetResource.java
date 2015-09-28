package com.petd.main.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.petd.dao.PetDao;
import com.petd.model.Pet;

public class PetResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;	
	String id;
	
	public PetResource(UriInfo uriInfo, Request request, String id){
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;				
	}
	
	//Application Integration
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Pet getPet(){
		Pet pet = PetDao.instance.getModel().get(id);
		if(pet==null)
			throw new RuntimeException("Get: Pet with " + id + " not found");
		return pet;
	}
	
	//Browser Integration
	@GET
	@Produces(MediaType.TEXT_XML)
	public Pet getPetHtml(){
		Pet pet = PetDao.instance.getModel().get(id);
		if(pet==null)
			throw new RuntimeException("Get: Pet with " + id + " not found");
		return pet;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putPet(JAXBElement<Pet> pet){
		Pet c = pet.getValue();
		return putAndGetResponse(c);
	}
	
	@DELETE
	public void deletePet(){
		Pet c = PetDao.instance.getModel().remove(id);
		if(c==null)
			throw new RuntimeException("Delete: Pet with " + id + " not found");
	}
	
	private Response putAndGetResponse(Pet pet){
		Response res;
		
		if(PetDao.instance.getModel().containsKey(pet.getId()))
			res = Response.noContent().build();
		else
			res = Response.created(uriInfo.getAbsolutePath()).build();
		
		PetDao.instance.getModel().put(pet.getId(), pet);
		return res;		
	}
	
}
