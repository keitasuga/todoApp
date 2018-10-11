package com.sugaharakeita.todo.todoApp.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Todo名を入力してください")
    @Length(max = 30, message = "{max}文字以内で入力してください")
    private String name;

    @NotNull(message = "期限を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "limit_date")
    private LocalDate limit;

    private boolean status;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate CreatedAt;

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

    public LocalDate getLimit() {
        return limit;
    }

    public void setLimit(LocalDate limit) {
        this.limit = limit;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return CreatedAt;
    }

    private void setCreatedAt(LocalDate createdAt) {
        CreatedAt = createdAt;
    }

    @Override
    public String toString() {
        return "Todo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", limit=" + limit +
            ", status=" + status +
            ", CreatedAt=" + CreatedAt +
            '}';
    }

    @PrePersist
    public void prePersist() {
        CreatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        CreatedAt = LocalDate.now();
    }
}
