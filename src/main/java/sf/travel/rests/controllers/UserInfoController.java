package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sf.travel.entities.Product;
import sf.travel.entities.UserInfo;
import sf.travel.rests.types.*;
import sf.travel.services.UserInfoService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Api(tags = "User")
public class UserInfoController {
    @Autowired
    private final UserInfoService userInfoService;

    @GetMapping("")
    @ApiOperation("Find All")
    public ApiResponse<UserInfo> findAll(@ModelAttribute UserInfoFilter filter){
        Page<UserInfo> pageResult = userInfoService.findAll(filter);
        ApiResponse<UserInfo> response = new ApiResponse<>();
        response.setItems(pageResult.getContent());
        response.setTotal(pageResult.getTotalElements());
        response.setSize(pageResult.getSize());
        response.setPage(pageResult.getNumber());

        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get one")
    public Optional<UserInfo> findById(@PathVariable Long id){
        return userInfoService.findById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update")
    public UserInfo partialUpdate(@PathVariable Long id, @RequestBody UpdateUserInfoReq req){
        return userInfoService.partialUpdate(id, req);
    }
}
