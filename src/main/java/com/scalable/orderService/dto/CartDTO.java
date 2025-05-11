package com.scalable.orderService.dto;

import java.util.List;

public class CartDTO {
    private Long userId;
    private List<CartItemDTO> items;
    private double finalTotal;
    
    
    
	public CartDTO(Long userId, List<CartItemDTO> items, double finalTotal) {
		super();
		this.userId = userId;
		this.items = items;
		this.finalTotal = finalTotal;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<CartItemDTO> getItems() {
		return items;
	}
	public void setItems(List<CartItemDTO> items) {
		this.items = items;
	}
	public double getFinalTotal() {
		return finalTotal;
	}
	public void setFinalTotal(double finalTotal) {
		this.finalTotal = finalTotal;
	}

    
}

