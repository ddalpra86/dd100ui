package org.dalpra.acme.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.dalpra.acme.rest.entities.Fruit;
import org.primefaces.shaded.json.JSONObject;

@Named("fruitController")
@ApplicationScoped
public class FruitController {
	private List<Fruit> fruits;
	private long idFruit;
	private String fruitName;
	private String fruitDescription;

	private final String SERVICE_AGGREGATOR_URI= "http://localhost:8080/fruits";
	Client client;
	WebTarget serviceTarget;

	@PostConstruct
	public void init() {
		fruits = new ArrayList<>();
		client = ClientBuilder.newClient();
		serviceTarget = client.target(SERVICE_AGGREGATOR_URI);
	}

	@PreDestroy
	public void destroy() {
		client.close();
	}
	
	public String placeFruit() {
		
		Fruit fruit = new Fruit();
		fruit.setId(null);
		fruit.setName(fruitName);
		fruit.setDescription(fruitDescription);
		
		
		
		 /*JSONObject obj = new JSONObject();

		 
	      obj.put("name",fruitName);
	      obj.put("description",fruitDescription);		
		
	      System.out.println(obj.toString());

	      Invocation.Builder invocationBuilder = serviceTarget.request(MediaType.APPLICATION_JSON);
	      Response response = invocationBuilder.post(Entity.entity(obj, MediaType.APPLICATION_JSON));

	      System.out.println(response.getStatus());
	      System.out.println(response.readEntity(String.class));*/
		
		Response response = serviceTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(fruit, MediaType.APPLICATION_JSON));
		System.out.println(response.getStatus());
		if(response.getStatus()==201) {
			System.out.println(response.getStatus());
			fruitName = new String();
			fruitDescription = new String();
		}
		
		return "fruits?faces-redirect=true";
	}

	public List<Fruit> getFruits() {
		List<Fruit> fromREST = serviceTarget
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Fruit>>() {
                });
		setFruits(fromREST);
		return fruits;
	}

	public void setFruits(List<Fruit> fruits) {
		this.fruits = fruits;
	}

	public long getIdFruit() {
		return idFruit;
	}

	public void setIdFruit(long idFruit) {
		this.idFruit = idFruit;
	}

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public String getFruitDescription() {
		return fruitDescription;
	}

	public void setFruitDescription(String fruitDescription) {
		this.fruitDescription = fruitDescription;
	}
	
}
