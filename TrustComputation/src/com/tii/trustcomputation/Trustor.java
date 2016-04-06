package com.tii.trustcomputation;

import java.io.Serializable;

public class Trustor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String description;
	//set
	public void setId(String id){
		this.id = id;
	}	
	public void setDescription(String description){
		this.description = description;
	}
	//get
	public String getId(){
		return id;
	}
	public String getDescription(){
		return description;
	}
}
