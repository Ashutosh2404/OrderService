package com.scalable.orderService.dto;

public class CartItemDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    
    
    
	public CartItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CartItemDTO(Long productId, String productName, int quantity, double unitPrice, double totalPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

    
    
}

