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
}
