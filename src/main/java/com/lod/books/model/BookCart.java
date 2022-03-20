package com.lod.books.model;

import com.lod.books.model.enumerations.BookCartStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class BookCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Book> products;

    @Enumerated(EnumType.STRING)
    private BookCartStatus status;

    public BookCart() {
    }

    public BookCart(User user) {
//        this.id=(long)(Math.random()*1000);

        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = BookCartStatus.CREATED;
    }


}

