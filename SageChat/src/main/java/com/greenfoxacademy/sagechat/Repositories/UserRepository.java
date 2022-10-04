package com.greenfoxacademy.sagechat.Repositories;

import com.greenfoxacademy.sagechat.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Boolean existsUserByLogin(String login);

    public Boolean existsUserByUsername(String login);

    public User findFirstByLogin(String login);

    public User findFirstById(Long id);




}
