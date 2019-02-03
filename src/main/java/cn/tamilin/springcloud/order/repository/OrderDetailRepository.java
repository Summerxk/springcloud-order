package cn.tamilin.springcloud.order.repository;

import cn.tamilin.springcloud.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName OrderDetailRepository
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/7 23:31
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
