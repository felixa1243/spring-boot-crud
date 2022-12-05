package com.iqbalnetwork.services;

import com.iqbalnetwork.models.responses.UserResponse;

public interface IUserService {
    UserResponse[] getAll();
    UserResponse findById(String id);
}
