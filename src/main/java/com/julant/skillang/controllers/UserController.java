package com.julant.skillang.controllers;

import com.julant.skillang.model.User;
import com.julant.skillang.exception.UserException;
import com.julant.skillang.dto.UpdateUserRequest;
import com.julant.skillang.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi User");
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMe(@RequestHeader("Authorization") String authorizationHeader) throws UserException {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return ResponseEntity.ok(userService.findUserByJwt(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String query) {
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateUserHandler(@RequestBody UpdateUserRequest request, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserByJwt(token);
        if (user == null) {
            throw new BadCredentialsException("Invalid Jwt Token");
        }
        userService.updateUser(user.getId(), request);
        return ResponseEntity.ok().build();
    }

}
