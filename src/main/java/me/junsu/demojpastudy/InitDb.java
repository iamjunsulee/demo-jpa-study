package me.junsu.demojpastudy;

import lombok.RequiredArgsConstructor;
import me.junsu.demojpastudy.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initDb();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void initDb() {
            Item item1 = new Item("JPA Book1", 100, 10000);
            Item item2 = new Item("JPA Book2", 100,20000);
            Item item3 = new Item("JPA Book3", 50, 25000);
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);

            Address address1 = new Address("서울특별시", "마포구 마포대로", "04413");
            Address address2 = new Address("부산광역시", "금정구", "01111");
            //회원 생성
            Member member = new Member("이준수", address1);
            Member member2 = new Member("삼준수", address2);
            em.persist(member);
            em.persist(member2);

            //주문상품 생성
            OrderItem orderItem1 = OrderItem.createOrderItem(item1, 10000,2);
            OrderItem orderItem2 = OrderItem.createOrderItem(item2, 20000,1);
            OrderItem orderItem3 = OrderItem.createOrderItem(item3, 25000,1);
            em.persist(orderItem1);
            em.persist(orderItem2);
            em.persist(orderItem3);

            //배송생성
            Delivery delivery = new Delivery(address1);
            Delivery delivery2 = new Delivery(address2);
            em.persist(delivery);
            em.persist(delivery2);

            //주문생성
            Order order = Order.createOrder(member, delivery, orderItem2);
            em.persist(order);

            Order order2 = Order.createOrder(member2, delivery2, orderItem1, orderItem3);
            em.persist(order2);
        }
    }
}
