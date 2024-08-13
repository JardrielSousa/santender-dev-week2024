package com.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByAccountNumber(String accountNumber);

}
