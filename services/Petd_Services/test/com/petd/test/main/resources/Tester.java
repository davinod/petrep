package com.petd.test.main.resources;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.petd.model.Pet;
import com.petd.model.Pet.Type;
import com.petd.dao.PetDao;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget service = client.target(getBaseURI());

	    // create one pet
	    
	    int newId = PetDao.instance.getModel().size() + 1;
	    
	    Pet pet = new Pet(String.valueOf(newId),"Scarlet", Type.KITTEN);
	    Response response = service.path("pets").path(pet.getId()).request(MediaType.APPLICATION_XML).put(Entity.entity(pet,MediaType.APPLICATION_XML),Response.class);

	    // Return code should be 201 == created resource
	    System.out.println(response.getStatus());

	    // Get the Pets
	    System.out.println(service.path("pets").request().accept(MediaType.TEXT_XML).get(String.class));

	    //Get JSON for application
	    System.out.println(service.path("pets").request().accept(MediaType.APPLICATION_JSON).get(String.class));

	    // Get XML for application
	    System.out.println(service.path("pets").request().accept(MediaType.APPLICATION_XML).get(String.class));

	    //Get Pet with id 1
	    Response checkDelete = service.path("pets/1").request().accept(MediaType.APPLICATION_XML).get();
	    System.out.println(checkDelete.getStatus());
	    
	    //Delete Pet with id 1
	    service.path("pets/1").request().delete();

	    //Get all Pets id 1 should be deleted
	    System.out.println(service.path("pets").request().accept(MediaType.APPLICATION_XML).get(String.class));

	    //Create a Pet
	    Form form = new Form();
	    
	    newId ++ ;	    
	    form.param("id", String.valueOf(newId));
	    form.param("name","Fram");
	    form.param("type","DOG");
	    
	    response = service.path("pets").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Response.class);
	    System.out.println("Form response " + response.getStatus());

	    //Get all the pets again
	    System.out.println(service.path("pets").request().accept(MediaType.APPLICATION_XML).get(String.class));

	  }

	  private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8888/Petd_Services").build();
	  }

}
