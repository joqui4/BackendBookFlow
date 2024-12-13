package com.BookFlow.usuarios.clubs.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ClubName(String clubTitle) {
    public ClubName {
        if (clubTitle == null || clubTitle.isBlank()) {
            throw new IllegalArgumentException("Club content is mandatory");
        }
        if (clubTitle.length() > 60) {
            throw new IllegalArgumentException("Club content must not exceed 60 characters");
        }
    }

    public String clubTitle() {
        return clubTitle;
    }
}