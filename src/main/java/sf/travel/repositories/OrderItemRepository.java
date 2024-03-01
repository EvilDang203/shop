package sf.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sf.travel.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {
}
