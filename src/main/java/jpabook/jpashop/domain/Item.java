package jpabook.jpashop.domain;

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


}
