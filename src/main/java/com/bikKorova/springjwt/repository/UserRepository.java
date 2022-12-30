package com.bikKorova.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bikKorova.springjwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  User findUserByUsername(String username);
  List<UserStatisticView> getAllBy();
  @Query(value = "select * from users where users.attemp_count!=0",nativeQuery = true)
  List<UserStatisticView> getAllUserStatistic();
}
