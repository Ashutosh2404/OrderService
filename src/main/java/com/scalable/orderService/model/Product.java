package com.scalable.orderService.model;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
@Immutable
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
    private String productName;
    private String description;
    private double unitPrice;
    private int quatity;
    
	public Product() {
		super();
		
	}

	public Product(String productName, String description, double unitPrice, int quatity) {
		super();
		this.productName = productName;
		this.description = description;
		this.unitPrice = unitPrice;
		this.quatity = quatity;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuatity() {
		return quatity;
	}

	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	
	
	
}
