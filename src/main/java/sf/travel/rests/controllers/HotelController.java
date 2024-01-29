package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sf.travel.entities.Hotel;
import sf.travel.rests.types.ApiResponse;
import sf.travel.rests.types.CreateHotelReq;
import sf.travel.rests.types.HotelFilter;
import sf.travel.rests.types.UpdateHotelReq;
import sf.travel.services.HotelService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
@AllArgsConstructor
@Api(tags = "Hotel")
public class HotelController {
    @Autowired private final HotelService hotelService;

    @PostMapping("/")
    @ApiOperation("Create")
    public Hotel create(@Valid @RequestBody CreateHotelReq req){
        return hotelService.create(req);
    }

    @GetMapping("")
    @ApiOperation("Find All")
    public ApiResponse<Hotel> findAll(@ModelAttribute HotelFilter filter){
        Page<Hotel> pageResult = hotelService.findAll(filter);
        ApiResponse<Hotel> response = new ApiResponse<>();
        response.setItems(pageResult.getContent());
        response.setTotal(pageResult.getTotalElements());
        response.setSize(pageResult.getSize());
        response.setPage(pageResult.getNumber());

        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one")
    public Optional<Hotel> findById(@PathVariable Long id){
        return hotelService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete")
    public void delete(@PathVariable Long id){
        hotelService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update")
    public Hotel partialUpdate(@PathVariable Long id, @RequestBody UpdateHotelReq req){
        return hotelService.partialUpdate(id, req);
    }
}
