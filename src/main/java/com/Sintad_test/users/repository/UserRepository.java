package com.Sintad_test.users.repository;

import com.Sintad_test.users.models.entities.User;
import com.Sintad_test.users.models.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserEntityByEmail(String email);
    boolean existsUserEntityByEmail(String email);
}
