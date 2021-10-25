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
import java.util.Map;
import java.util.stream.Collectors;

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
        //for 문 돌면서 쿼리를 실행하므로 처음 조회한 결과가 N건이면 N번 실행하게 된다.
        orders.forEach(o -> {
            List<OrderItemQueryDto> orderItems = orderQueryRepository.findOrderItems(o.getOrderId());   //N번 실행
            o.setOrderItems(orderItems);
        });
        //결국엔 N + 1 문제 직면
        return orders;
    }

    public List<OrderQueryDto> findOrderQueryDto2() {
        List<OrderQueryDto> orders = orderQueryRepository.findOrders(); //1번 조회 -> 결과 N
        List<Long> ids = orders.stream().map(OrderQueryDto::getOrderId).collect(Collectors.toList());
        List<OrderItemQueryDto> orderItem = orderQueryRepository.findOrderItems2(ids);  // N건만큼 in 절로 한번에 조회

        //orderId를 Key로 가지는 Map 생성
        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItem.stream().collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
        //Map 들어있는 orderItem으로 OrderQueryDto에 넣어준다.
        orders.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return orders;
    }
}
