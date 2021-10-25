package me.junsu.demojpastudy.service;

import lombok.RequiredArgsConstructor;
import me.junsu.demojpastudy.domain.Order;
import me.junsu.demojpastudy.repository.order.query.OrderItemQueryDto;
import me.junsu.demojpastudy.repository.order.query.OrderQueryDto;
import me.junsu.demojpastudy.repository.order.query.OrderQueryRepository;
import me.junsu.demojpastudy.repository.SimpleOrderDto;
import me.junsu.demojpastudy.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllWithMemberDelivery() {
        return orderRepository.findAllWithMemberDelivery();
    }

    public List<SimpleOrderDto> findSimpleOrderDto() {
        return orderRepository.findSimpleOrderDto();
    }

    public List<Order> findAllWithOrderItem() {
        return orderRepository.findAllWithOrderItem();
    }

    public List<OrderQueryDto> findOrderQueryDto() {
        List<OrderQueryDto> orders = orderQueryRepository.findOrders(); //1번 조회 -> 결과 N 

        //toMany 관계는 따로 처리
        orders.forEach(o -> {
            List<OrderItemQueryDto> orderItems = orderQueryRepository.findOrderItems(o.getOrderId());   //N번 실행
            o.setOrderItems(orderItems);
        });
        //결국엔 N + 1 문제 직면
        return orders;
    }
}
