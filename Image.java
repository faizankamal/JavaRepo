package com.example.demo;

import java.io.Serializable;

public class Image implements Serializable{
 
	String name;
	
	String age;

	public Image(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public Image() {

	}
	
}
