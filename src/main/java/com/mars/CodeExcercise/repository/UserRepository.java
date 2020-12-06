package com.mars.CodeExcercise.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.CodeExcercise.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
