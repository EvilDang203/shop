package sf.travel.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sf.travel.entities.Customer;
import sf.travel.entities.Order;
import sf.travel.entities.Travel;
import sf.travel.errors.ConflictError;
import sf.travel.errors.ErrorCode;
import sf.travel.helper.specifications.OrderSpec;
import sf.travel.repositories.CustomerRepository;
import sf.travel.repositories.OrderRepository;
import sf.travel.repositories.TravelRepository;
import sf.travel.rests.types.CreateOrderReq;
import sf.travel.rests.types.OrderFilter;
import sf.travel.rests.types.UpdateOrderReq;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
}
