package com.greenfoxacademy.sagechat.Repositories;

import com.greenfoxacademy.sagechat.Models.Channel;
import com.greenfoxacademy.sagechat.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByChannel(Channel channel);

    List<Post> findPostsByChannel_Name(String channel);
}
