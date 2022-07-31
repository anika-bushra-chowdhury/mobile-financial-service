package com.anika.mobilefinancialservice.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseDomain {
    @Column(name = "CREATED", nullable = false)
    private Date created;

    @Column(name = "UPDATED")
    private Date updated;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @PrePersist
    public void onCreate() {
        this.created = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = new Date();
    }
}
