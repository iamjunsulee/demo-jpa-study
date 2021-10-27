package me.junsu.demojpastudy.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item{
    private String author;

    @Builder
    public Book(String name, int stockQuantity, int price, String author) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.author = author;
    }
}
