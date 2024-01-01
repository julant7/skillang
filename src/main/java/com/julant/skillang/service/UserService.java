package com.julant.skillang.service;

import com.julant.skillang.model.User;
import com.julant.skillang.exception.UserException;
import com.julant.skillang.dto.UpdateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    User getInfo(String token) throws UserException;

    User findUserById(Long id) throws UserException;
    User updateUser(Long userId, UpdateUserRequest req) throws UserException;

    List<User> searchUser(String query);

    User findUserByJwt(String jwt) throws UserException;

}
