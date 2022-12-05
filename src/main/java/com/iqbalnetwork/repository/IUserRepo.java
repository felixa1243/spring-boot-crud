package com.iqbalnetwork.repository;

import com.iqbalnetwork.models.responses.UserResponse;

public interface IUserRepo {
    UserResponse[] getAll();
    UserResponse getById(String id);
}
