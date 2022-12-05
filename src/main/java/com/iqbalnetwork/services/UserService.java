package com.iqbalnetwork.services;

import com.iqbalnetwork.models.responses.UserResponse;
import com.iqbalnetwork.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepo repo;

    @Override
    public UserResponse[] getAll() {
        return repo.getAll();
    }

    @Override
    public UserResponse findById(String id) {
        return repo.getById(id);
    }
}
