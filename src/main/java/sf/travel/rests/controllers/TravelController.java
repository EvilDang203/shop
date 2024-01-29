package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sf.travel.entities.Travel;
import sf.travel.rests.types.ApiResponse;
import sf.travel.rests.types.CreateTravelReq;
import sf.travel.rests.types.TravelFilter;
import sf.travel.rests.types.UpdateTravelReq;
import sf.travel.services.TravelService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/travels")
@AllArgsConstructor
@Api(tags = "Travel")
public class TravelController {
    @Autowired
    private final TravelService travelService;

    @PostMapping("/")
    @ApiOperation("Create")
    public Travel create(@Valid @RequestBody CreateTravelReq req) {
        return travelService.create(req);
    }

    @GetMapping("")
    @ApiOperation("Find All")
    public ApiResponse<Travel> findAll(@ModelAttribute TravelFilter filter){
        Page<Travel> pageResult = travelService.findAll(filter);
        ApiResponse<Travel> response = new ApiResponse<>();
        response.setItems(pageResult.getContent());
        response.setTotal(pageResult.getTotalElements());
        response.setSize(pageResult.getSize());
        response.setPage(pageResult.getNumber());

        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one")
    public Optional<Travel> findById(@PathVariable Long id){
        return travelService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete")
    public void delete(@PathVariable Long id){
        travelService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update")
    public Travel partialUpdate(@PathVariable Long id, @RequestBody UpdateTravelReq req){
        return travelService.partialUpdate(id, req);
    }
}
