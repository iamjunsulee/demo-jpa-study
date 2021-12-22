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
            Book item1 = new Book("Spring Book", 100, 10000, "lee");
            Book item2 = new Book("Summer Book", 100,20000, "lee");
            Book item3 = new Book("Fall Book", 50, 25000, "lee");
            Book item4 = new Book("Winter Book", 50, 30000, "lee");
            Book item5 = new Book("Spring Data JPA", 11, 35000, "lee");
            Book item6 = new Book("SpringFramework 5.0", 29, 30000, "lee");
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);
            em.persist(item4);
            em.persist(item5);
            em.persist(item6);

            Address address1 = new Address("서울특별시", "마포구 마포대로", "04413");
            Address address2 = new Address("부산광역시", "금정구", "01111");
            Address address3 = new Address("서울특별시", "마포구", "01112");
            Address address4 = new Address("서울특별시", "강서구", "01113");
            Address address5 = new Address("서울특별시", "강남구", "01114");
            Address address6 = new Address("서울특별시", "금정구", "01115");
            //회원 생성
            Member member = new Member("이준수", address1);
            Member member2 = new Member("삼준수", address2);
            Member member3 = new Member("사준수", address3);
            Member member4 = new Member("오준수", address4);
            Member member5 = new Member("육준수", address5);
            Member member6 = new Member("칠준수", address6);
            em.persist(member);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            em.persist(member5);
            em.persist(member6);

            //주문상품 생성
            OrderItem orderItem1 = OrderItem.createOrderItem(item1, 10000,1);
            OrderItem orderItem2 = OrderItem.createOrderItem(item2, 20000,2);
            OrderItem orderItem3 = OrderItem.createOrderItem(item3, 25000,3);
            OrderItem orderItem4 = OrderItem.createOrderItem(item4, 30000,4);
            em.persist(orderItem1);
            em.persist(orderItem2);
            em.persist(orderItem3);
            em.persist(orderItem4);

            //배송생성
            Delivery delivery = new Delivery(address1);
            Delivery delivery2 = new Delivery(address2);
            em.persist(delivery);
            em.persist(delivery2);

            //주문생성
            Order order = Order.createOrder(member, delivery, orderItem4, orderItem2);
            em.persist(order);

            Order order2 = Order.createOrder(member2, delivery2, orderItem1, orderItem3);
            em.persist(order2);
        }
    }
}
