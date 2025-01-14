package com.Sintad_test.users.service;

import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.users.interfaces.GetUserData;
import com.Sintad_test.users.models.entities.User;
import com.Sintad_test.users.repository.UserRepository;
import com.Sintad_test.utils.Messages;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements GetUserData {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserEntityByEmail(username);
        if(optionalUser.isEmpty()) {
            throw new NotFoundException(Messages.USER_NOT_FOUND.getMessage());
        }
        return optionalUser.get();
    }
}
