package com.iqbalnetwork.controllers;

import com.iqbalnetwork.controllers.exceptions.NotFoundException;
import com.iqbalnetwork.models.responses.SuccessResponse;
import com.iqbalnetwork.models.responses.UserResponse;
import com.iqbalnetwork.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    IUserService service;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(new SuccessResponse<>("get data success", service.getAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable("id") String id) throws Exception {
        UserResponse result = service.findById(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.status(200).body(new SuccessResponse<>("get data success", result));
    }
}
