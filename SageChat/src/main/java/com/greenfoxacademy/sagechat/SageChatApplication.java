package com.greenfoxacademy.sagechat;

import com.greenfoxacademy.sagechat.Models.Channel;
import com.greenfoxacademy.sagechat.Repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SageChatApplication implements CommandLineRunner {

    //ChannelRepository channelRepository;

    //@Autowired
    //public SageChatApplication(ChannelRepository channelRepository) {
    //   this.channelRepository = channelRepository;
    //}

    public static void main(String[] args) {
        SpringApplication.run(SageChatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Channel channel1 = new Channel("General");
        //Channel channel2 = new Channel("Paridas");

        //channelRepository.save(channel1);
        //channelRepository.save(channel2);

    }
}
