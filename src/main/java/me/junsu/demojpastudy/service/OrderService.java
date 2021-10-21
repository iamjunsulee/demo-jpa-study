package me.junsu.demojpastudy.service;

import lombok.RequiredArgsConstructor;
import me.junsu.demojpastudy.domain.Order;
import me.junsu.demojpastudy.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllWithMemberDelivery() {
        return orderRepository.findAllWithMemberDelivery();
    }
}
