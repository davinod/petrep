package com.petd.test.main;

import static org.junit.Assert.*;

import org.junit.Test;

import com.petd.model.Pet;
import com.petd.model.Pet.Type;

public class PetTest {

	@Test
	public final void testGetName() {
		Pet pet = new Pet();
		String name = "Ugly Dog";
		pet.setName(name);
		assertEquals(pet.getName(), name);		
	}

	@Test
	public final void testSetName() {
		Pet pet = new Pet();
		String name = "Ugly Dog";
		pet.setName(name);
		assertEquals(pet.getName(), name);
	}

	@Test
	public final void testGetDogType() {
		Pet pet = new Pet();
		Pet.Type type = Type.DOG;
		pet.setType(type);
		assertEquals(pet.getType(), type);
	}

	@Test
	public final void testSetType() {
		Pet pet = new Pet();
		
		Pet.Type type = Type.DOG;
		pet.setType(type);
		assertEquals(pet.getType(), type);
		
		type = Type.PUPPY;
		pet.setType(type);
		assertEquals(pet.getType(), type);
		
		type = Type.CAT;
		pet.setType(type);
		assertEquals(pet.getType(), type);
		
		type = Type.KITTEN;
		pet.setType(type);
		assertEquals(pet.getType(), type);
				
	}

	@Test
	public final void testToString() {
		Pet pet = new Pet();
		String expectedMessage = "Pet [name=Ugly Dog, type=DOG]";
		
		pet.setName("Ugly Dog");
		pet.setType(Type.DOG);
		
		assertEquals(pet.toString(), expectedMessage);
	}

}
