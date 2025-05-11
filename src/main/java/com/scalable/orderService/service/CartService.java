package com.scalable.orderService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalable.orderService.dto.CartDTO;
import com.scalable.orderService.dto.CartItemDTO;
import com.scalable.orderService.model.Cart;
import com.scalable.orderService.model.CartItem;
import com.scalable.orderService.model.Product;
import com.scalable.orderService.repo.CartItemRepository;
import com.scalable.orderService.repo.CartRepository;
import com.scalable.orderService.repo.ProductRepo;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepo productRepo;

    public void addItem(Long userId, Long productId, int quantity) {
    	
    	 Optional<Product> product = productRepo.findById(productId);
    	    if (!product.isPresent()) {
    	        throw new IllegalArgumentException("Product not found");
    	    }
    	    
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet (() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });

        Optional<CartItem> existingItemOpt = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cartItemRepository.save(newItem);
        }
    }

    public void removeItem(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        cartItemRepository.delete(item);
    }

    public CartDTO getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());
        
        double totalPrice = 0.0;
        List<CartItemDTO> itemDTOs = new ArrayList<CartItemDTO>();

        for (CartItem item : cart.getItems()) {
        	Product product = productRepo.findById(item.getProductId()).orElse(null);
            if (product != null) {
                double itemTotal = product.getUnitPrice() * item.getQuantity();
                totalPrice += itemTotal;

                itemDTOs.add(new CartItemDTO(
                	    product.getProductId(),
                	    product.getProductName(),
                	    item.getQuantity(),
                	    product.getUnitPrice(),
                	    itemTotal
                	));

            }
        }

        return new CartDTO(userId, itemDTOs, totalPrice);
    }



    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cartItemRepository.deleteAll(cart.getItems());
    }

}

