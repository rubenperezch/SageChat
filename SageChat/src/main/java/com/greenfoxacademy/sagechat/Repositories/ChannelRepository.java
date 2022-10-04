package com.greenfoxacademy.sagechat.Repositories;

import com.greenfoxacademy.sagechat.Models.Channel;
import com.greenfoxacademy.sagechat.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    public List<Channel> findAll();

    public Channel findFirstByName(String name);

    public Boolean existsChannelByName(String name);

}
