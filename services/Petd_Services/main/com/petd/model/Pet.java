package com.petd.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pet {

	public enum Type {
		DOG,
		PUPPY,
		CAT,
		KITTEN
	}
	
	private String id ;
	private String name;
	private Type type;
	
	public Pet(){
		
	}
	
	public Pet(String id, String name, Type type){
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Pet [name=" + name + ", type=" + type + "]";
	}
		
}
