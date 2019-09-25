package com.aimango.message.controller;

import com.aimango.message.dao.TestMapper;
import com.aimango.message.pojo.User;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/")
@RestController
public class Controller {
    @Autowired
    private TestMapper testMapper;

    @Autowired
    private PulsarClient pulsarClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping(path = "test")
    public String test(){
        return "test";
    }

    @GetMapping(path = "pulsar")
    public String pulsarProducer(){
//        PulsarClient client=null;
//        try {
//            client = PulsarClient.builder().serviceUrl("pulsar://pulsar.aimango.net:6650").build();
//        } catch (PulsarClientException e) {
//            e.printStackTrace();
//        }
        MessageId messageId=null;
        try {
            Producer<byte[]> producer=pulsarClient.newProducer().topic("default").create();
            messageId=producer.send("xxxxxx".getBytes());
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
        if(messageId!=null) {
            return "success";
        }else {
            return "false";
        }
    }


    @GetMapping(path = "mapper")
    public String mapperTest(){
        User user=testMapper.getTest("liuchaozhi","123456");
        if (user!=null){
            return "success";
        }else {
            return "false";
        }
    }

    @GetMapping(path = "redis")
    public String redisTest(){
        redisTemplate.opsForValue().set("xxx","aaa");
        String res=redisTemplate.opsForValue().get("xxx");
        return res;
    }
}
