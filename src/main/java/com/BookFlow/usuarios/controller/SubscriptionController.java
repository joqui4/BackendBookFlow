package com.BookFlow.usuarios.controller;

import com.BookFlow.usuarios.domain.Subscription;
import com.BookFlow.usuarios.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribe/{userId}")
    public ResponseEntity<Subscription> subscribe(@PathVariable Long userId) {
        Subscription subscription = subscriptionService.createSubscription(userId);
        if (subscription != null) {
            return ResponseEntity.ok(subscription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable Long userId) {
        Optional<Subscription> subscription = subscriptionService.getSubscriptionByUserId(userId);
        return subscription.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}
