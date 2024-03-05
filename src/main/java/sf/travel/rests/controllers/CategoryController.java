package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sf.travel.entities.Category;
import sf.travel.rests.types.*;
import sf.travel.services.CategoryService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Api(tags = "Category")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @PostMapping("/")
    @ApiOperation("Create")
    public Category create(@Valid @RequestBody CreateCategoryReq req) {
        return categoryService.create(req);
    }

    @GetMapping("")
    @ApiOperation("Find All")
    public ApiResponse<Category> findAll(@ModelAttribute CategoryFilter filter){
        Page<Category> pageResult = categoryService.findAll(filter);
        ApiResponse<Category> response = new ApiResponse<>();
        response.setItems(pageResult.getContent());
        response.setTotal(pageResult.getTotalElements());
        response.setSize(pageResult.getSize());
        response.setPage(pageResult.getNumber());

        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one")
    public Optional<Category> findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update")
    public Category partialUpdate(@PathVariable Long id, @RequestBody UpdateCategoryReq req){
        return categoryService.partialUpdate(id, req);
    }
}
