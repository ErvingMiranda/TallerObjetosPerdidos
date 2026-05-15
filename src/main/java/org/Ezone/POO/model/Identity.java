package org.Ezone.POO.model;

import java.util.UUID;

public abstract class Identity {
    private final String id;

    public Identity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}