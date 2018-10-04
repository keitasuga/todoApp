package com.sugaharakeita.todo.todoApp.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public abstract class TimestampEntity {

    private Timestamp updatedTime;

    @Column(updatable = false)
    private Timestamp createdTime;

    @PrePersist
    public void prePersist() {
        Timestamp ts = new Timestamp((new Date()).getTime());
        createdTime = ts;
        updatedTime = ts;
    }

    @PreUpdate
    public void preUpdate() {
        updatedTime = new Timestamp((new Date()).getTime());
    }
}