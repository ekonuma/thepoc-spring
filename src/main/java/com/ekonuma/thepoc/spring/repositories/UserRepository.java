package com.ekonuma.thepoc.spring.repositories;

import com.ekonuma.thepoc.spring.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UUID, User> {
}
