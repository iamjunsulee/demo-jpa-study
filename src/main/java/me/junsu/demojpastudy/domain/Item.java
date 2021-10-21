package me.junsu.demojpastudy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;            //상품번호
    private String name;        //상품명
    private int stockQuantity;  //재고량
    private int price;          //가격

    @Builder
    public Item(String name, int stockQuantity, int price) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }
}
