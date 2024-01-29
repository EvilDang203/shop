package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sf.travel.entities.Highlight;
import sf.travel.rests.types.ApiResponse;
import sf.travel.rests.types.CreateHighlightReq;
import sf.travel.rests.types.HighlightFilter;
import sf.travel.rests.types.UpdateHighlightReq;
import sf.travel.services.HighlightService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/highlights")
@AllArgsConstructor
@Api(tags = "Highlight")
public class HighlightController {
    @Autowired
    private final HighlightService highlightService;

    @PostMapping("/")
    @ApiOperation("Create")
    public Highlight create(@Valid @RequestBody CreateHighlightReq req) {
        return highlightService.create(req);
    }

    @GetMapping("")
    @ApiOperation("Find All")
    public ApiResponse<Highlight> findAll(@ModelAttribute HighlightFilter filter){
        Page<Highlight> pageResult = highlightService.findAll(filter);
        ApiResponse<Highlight> response = new ApiResponse<>();
        response.setItems(pageResult.getContent());
        response.setTotal(pageResult.getTotalElements());
        response.setSize(pageResult.getSize());
        response.setPage(pageResult.getNumber());

        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one")
    public Optional<Highlight> findById(@PathVariable Long id){
        return highlightService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete")
    public void delete(@PathVariable Long id){
        highlightService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update")
    public Highlight partialUpdate(@PathVariable Long id, @RequestBody UpdateHighlightReq req){
        return highlightService.partialUpdate(id, req);
    }
}
