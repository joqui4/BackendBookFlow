package com.BookFlow.usuarios.service;

import com.BookFlow.usuarios.domain.Subscription;
import com.BookFlow.usuarios.domain.usuario;
import com.BookFlow.usuarios.repository.SubscriptionRepository;
import com.BookFlow.usuarios.repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private usuarioRepository usuarioRepository;

    public Subscription createSubscription(Long userId) {
        Optional<usuario> userOpt = usuarioRepository.findById(userId);
        if (userOpt.isPresent()) {
            usuario user = userOpt.get();
            Subscription subscription = new Subscription();
            subscription.setUser(user);
            subscription.setStartDate(LocalDate.now());
            subscription.setEndDate(LocalDate.now().plusMonths(1));
            subscription.setActive(true);
            subscription.setOnTrial(true);
            return subscriptionRepository.save(subscription);
        }
        return null;
    }

    public Optional<Subscription> getSubscriptionByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }
}
