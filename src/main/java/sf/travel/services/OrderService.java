package sf.travel.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sf.travel.entities.Order;
import sf.travel.entities.OrderItem;
import sf.travel.entities.Product;
import sf.travel.repositories.OrderRepository;
import sf.travel.repositories.ProductRepository;
import sf.travel.rests.types.AddOrderItemReq;
import sf.travel.rests.types.UpdateOrderItemReq;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder() {
        Order newOrder = new Order();
        return orderRepository.save(newOrder);
    }

    @Transactional
    public Order addOrderItem(Long orderId, AddOrderItemReq request) {
        // Tạo một đơn hàng mới nếu không tồn tại
        Order order = orderRepository.findById(orderId).orElseGet(this::createOrder);

        Product product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) {
            // handle product not found error
            return null;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(request.getQuantity());
        orderItem.setPrice(product.getPrice());

        order.getItems().add(orderItem);

        // update total cost
        int totalCost = calculateTotalCost(order.getItems());
        order.setTotalCost(totalCost);

        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrderItem(Long orderId, Long orderItemId, UpdateOrderItemReq request) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            // handle order not found error
            return null;
        }

        Order order = optionalOrder.get();

        OrderItem orderItem = order.getItems().stream()
                .filter(item -> item.getId().equals(orderItemId))
                .findFirst()
                .orElse(null);

        if (orderItem == null) {
            // handle order item not found error
            return null;
        }

        int newQuantity = request.getQuantity();
        if (newQuantity == 0) {
            // Remove the item if the quantity becomes 0
            order.getItems().remove(orderItem);
        } else {
            orderItem.setQuantity(newQuantity);
        }

        // update total cost
        int totalCost = calculateTotalCost(order.getItems());
        order.setTotalCost(totalCost);

        return orderRepository.save(order);
    }

    @Transactional
    public Order removeOrderItem(Long orderId, Long orderItemId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            // handle order not found error
            return null;
        }

        Order order = optionalOrder.get();
        List<OrderItem> items = order.getItems();

        items.removeIf(item -> item.getId().equals(orderItemId));

        // update total cost
        int totalCost = calculateTotalCost(items);
        order.setTotalCost(totalCost);

        return orderRepository.save(order);
    }

    private int calculateTotalCost(List<OrderItem> items) {
        int totalCost = 0;
        for (OrderItem item : items) {
            totalCost += item.getPrice() * item.getQuantity();
        }
        return totalCost;
    }
}
