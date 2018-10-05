package com.sugaharakeita.todo.todoApp.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Length(max = 30)
    private String name;

    @NotNull
    @Column(name = "limit_date")
    private Date limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLimit() {
        return limit;
    }

    public void setLimit(Date limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Todo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", limit=" + limit +
            '}';
    }
}
