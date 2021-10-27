package me.junsu.demojpastudy.domain;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    public Long id;            //상품번호
    public  String name;        //상품명
    public int stockQuantity;  //재고량
    public int price;          //가격

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) throws Exception {
        int rest = stockQuantity - quantity;
        if (rest < 0) {
            throw new Exception("재고 없음");
        }
        this.stockQuantity = rest;
    }
}
