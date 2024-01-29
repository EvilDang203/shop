package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sf.travel.entities.Order;
import sf.travel.rests.types.ApiResponse;
import sf.travel.rests.types.CreateOrderReq;
import sf.travel.rests.types.OrderFilter;
import sf.travel.rests.types.UpdateOrderReq;
import sf.travel.services.OrderService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Api(tags = "Order")
public class OrderController {
    @Autowired private final OrderService orderService;

    @PostMapping("/")
    @ApiOperation("Create")
    public Order create(@Valid @RequestBody CreateOrderReq req) {
        return orderService.create(req);
    }

    @GetMapping("")
    @ApiOperation("Find All")
    public ApiResponse<Order> findAll(@ModelAttribute OrderFilter filter){
        Page<Order> pageResult = orderService.findAll(filter);
        ApiResponse<Order> response = new ApiResponse<>();
        response.setItems(pageResult.getContent());
        response.setTotal(pageResult.getTotalElements());
        response.setSize(pageResult.getSize());
        response.setPage(pageResult.getNumber());

        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one")
    public Optional<Order> findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete")
    public void delete(@PathVariable Long id){
        orderService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update")
    public Order partialUpdate(@PathVariable Long id, @RequestBody UpdateOrderReq req){
        return orderService.partialUpdate(id, req);
    }
}
