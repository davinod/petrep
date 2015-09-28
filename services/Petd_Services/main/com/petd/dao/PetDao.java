package com.petd.dao;

import java.util.HashMap;
import java.util.Map;

import com.petd.model.Pet;
import com.petd.model.Pet.Type;

public enum PetDao {
		instance;
		
		private Map<String, Pet> contentProvider = new HashMap<String, Pet>();
		
		private PetDao() {
			contentProvider.put("1",new Pet("1", "Preta", Type.CAT));			
			contentProvider.put("2",new Pet("2", "Atila", Type.DOG));
			contentProvider.put("3",new Pet("3", "Leka",  Type.DOG));
			contentProvider.put("4",new Pet("4", "Thor",  Type.PUPPY));			
		}
	
		public Map<String, Pet> getModel(){
			return contentProvider;
		}
	
}
