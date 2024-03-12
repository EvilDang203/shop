package sf.travel.rests.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sf.travel.rests.types.*;
import sf.travel.services.AuthService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Api(tags = "Authentication")
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/register")
    @ApiOperation("Register User")
    public ResponseEntity<String> registerUser(@Valid @RequestBody CreateRegisterReq request) {
        try {
            authService.registerUser(request);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    @ApiOperation("User Login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            String result = authService.login(username, password);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
        }
    }
}
