package com.nitsenkov.repository;

import com.nitsenkov.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID>, JpaRepository<User, UUID> {
}
