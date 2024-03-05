package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sf.travel.entities.Product;
import sf.travel.rests.types.*;
import sf.travel.services.ProductService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Api(tags = "Product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    @PostMapping("/")
    @ApiOperation("Create")
    public Product create(@Valid @RequestBody CreateProductReq req) {
        return productService.create(req);
    }

    @GetMapping("")
    @ApiOperation("Find All")
    public ApiResponse<Product> findAll(@ModelAttribute ProductFilter filter){
        Page<Product> pageResult = productService.findAll(filter);
        ApiResponse<Product> response = new ApiResponse<>();
        response.setItems(pageResult.getContent());
        response.setTotal(pageResult.getTotalElements());
        response.setSize(pageResult.getSize());
        response.setPage(pageResult.getNumber());

        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one")
    public Optional<Product> findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update")
    public Product partialUpdate(@PathVariable Long id, @RequestBody UpdateProductReq req){
        return productService.partialUpdate(id, req);
    }
}
