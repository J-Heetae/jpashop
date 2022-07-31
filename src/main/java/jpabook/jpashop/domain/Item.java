package jpabook.jpashop.domain;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int price;

    private int stockQuantity;

    private String thump_image;

    private LocalDateTime indate;

    //==비지니스 로직==//

    /*
    *  stock 증가
    * */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*
    *  stock 감소
    * */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
