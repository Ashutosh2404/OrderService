package com.scalable.orderService.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scalable.orderService.dto.CartDTO;


import com.scalable.orderService.service.CartService;



@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestParam Long userId,
                                          @RequestParam Long productId,
                                          @RequestParam int quantity) {
        cartService.addItem(userId, productId, quantity);
        return ResponseEntity.ok("Item added/updated in cart");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeItem(@RequestParam Long userId,
                                             @RequestParam Long productId) {
        cartService.removeItem(userId, productId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }


    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared");
    }

   
}