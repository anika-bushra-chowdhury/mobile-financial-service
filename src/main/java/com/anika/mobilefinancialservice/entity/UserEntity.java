package com.anika.mobilefinancialservice.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:27 PM
 */

public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    //TODO: add rest of the fields

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
