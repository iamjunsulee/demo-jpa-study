package me.junsu.demojpastudy.repository;

import me.junsu.demojpastudy.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //fetch join 사용
    @Query("select o from Order o join fetch o.member m join fetch o.delivery d")
    List<Order> findAllWithMemberDelivery();

    @Query("select new me.junsu.demojpastudy.repository.OrderQueryDto(o.id, m.name, o.orderStatus, o.orderDate, d.address) from Order o join o.member m join o.delivery d")
    List<OrderQueryDto> findOrderQueryDto();
}
