package com.Sintad_test.users.interfaces;

import com.Sintad_test.users.models.entities.User;
import com.Sintad_test.users.models.response.UserResponse;

public interface GetUserData {
    User getUserByUsername(String username);
}
