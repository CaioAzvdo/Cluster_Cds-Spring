package com.projeto.crud_spring.services;

import com.projeto.crud_spring.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getAuthenticatedUser();
}
