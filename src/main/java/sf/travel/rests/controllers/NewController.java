package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sf.travel.entities.New;
import sf.travel.rests.types.ApiResponse;
import sf.travel.rests.types.CreateNewReq;
import sf.travel.rests.types.NewFilter;
import sf.travel.rests.types.UpdateNewReq;
import sf.travel.services.NewService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
@Api(tags = "New")
public class NewController {
    @Autowired
    private final NewService newService;

    @PostMapping("/")
    @ApiOperation("Create")
    public New create(@Valid @RequestBody CreateNewReq req){
        return newService.create(req);
    }

    @GetMapping("")
    @ApiOperation("Find All")
    public ApiResponse<New> findAll(@ModelAttribute NewFilter filter){
        Page<New> pageResult = newService.findAll(filter);
        ApiResponse<New> response = new ApiResponse<>();
        response.setItems(pageResult.getContent());
        response.setTotal(pageResult.getTotalElements());
        response.setSize(pageResult.getSize());
        response.setPage(pageResult.getNumber());

        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one")
    public Optional<New> findById(@PathVariable Long id){
        return newService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete")
    public void delete(@PathVariable Long id){
        newService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update")
    public New partialUpdate(@PathVariable Long id, @RequestBody UpdateNewReq req){
        return newService.partialUpdate(id, req);
    }
}
