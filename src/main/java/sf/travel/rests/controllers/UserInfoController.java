package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sf.travel.services.UserInfoService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Api(tags = "User")
public class UserInfoController {
    @Autowired
    private final UserInfoService productService;
}
