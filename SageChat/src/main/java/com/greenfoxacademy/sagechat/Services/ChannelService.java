package com.greenfoxacademy.sagechat.Services;

import com.greenfoxacademy.sagechat.Models.Channel;
import com.greenfoxacademy.sagechat.Models.Post;
import com.greenfoxacademy.sagechat.Repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> listChannels() {
        return channelRepository.findAll();
    }

    public List<Post> findAllPostsByChannel(String name) {
        List<Post> channelPosts = channelRepository.findFirstByName(name).getChannelPosts();

        return channelPosts;
    }

    public Channel findFirstByName(String name) {
        return findFirstByName(name);
    }
}
