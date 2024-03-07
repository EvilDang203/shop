package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sf.travel.entities.Order;
import sf.travel.rests.types.*;
import sf.travel.services.OrderService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Api(tags = "Order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/")
    @ApiOperation("Create Order")
    public ResponseEntity<Order> createOrder() {
        Order newOrder = orderService.createOrder();
        return ResponseEntity.ok(newOrder);
    }

    @PostMapping("/{orderId}/items")
    @ApiOperation("Add Order Item")
    public ResponseEntity<Order> addOrderItem(@PathVariable Long orderId, @Valid @RequestBody AddOrderItemReq request) {
        Order updatedOrder = orderService.addOrderItem(orderId, request);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("/{orderId}/items/{orderItemId}")
    @ApiOperation("Update Order Item")
    public ResponseEntity<Order> updateOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId, @Valid @RequestBody UpdateOrderItemReq request) {
        Order updatedOrder = orderService.updateOrderItem(orderId, orderItemId, request);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}/items/{orderItemId}")
    @ApiOperation("Remove Order Item")
    public ResponseEntity<Void> removeOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        orderService.removeOrderItem(orderId, orderItemId);
        return ResponseEntity.noContent().build();
    }
}
