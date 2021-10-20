package me.junsu.demojpastudy.repository;

import me.junsu.demojpastudy.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
