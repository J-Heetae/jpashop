package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember("서울", "국민대학교", "123456");
        Item item = createItem("시계", 1000, 10);
        int orderCnt = 5;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCnt);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 주문상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량이다.", 1000 * 5, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량만큼 재고가 줄어야 한다.", 5, item.getStockQuantity());
    }


    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {

        //given
        Member member = createMember("서울", "국민대학교", "123456");
        Item item = createItem("시계", 1000, 1);
        int orderCnt = 2;

        //when
        orderService.order(member.getId(), item.getId(), orderCnt);

        //then
        fail("재고 수량 부족 예외가 발생해야 합니다.");
    }


    @Test
    public void 주문취소() throws Exception {

        //given
        Member member = createMember("서울", "국민대학교", "123456");
        Item item = createItem("시계", 1000, 10);
        int orderCnt = 5;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCnt);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("주문취소시 주문상태는 CANCEL", OrderStatus.CANCEL, getOrder.getStatus());
        Assert.assertEquals("주문이 취소된 상품은 재고수량이 다시 증가해야 합니다.", 10, item.getStockQuantity());
    }

    private Item createItem(String name, int price, int stockQuantity) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        em.persist(item);
        return item;
    }

    private Member createMember(String city, String street, String zipcode) {
        Member member = new Member();
        member.setUsername("userA");
        member.setAdrress(new Address(city, street, zipcode));
        em.persist(member);
        return member;
    }

}