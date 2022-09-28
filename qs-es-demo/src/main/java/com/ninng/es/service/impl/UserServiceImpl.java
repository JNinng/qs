package com.ninng.es.service.impl;

import com.ninng.es.domain.User;
import com.ninng.es.repository.UserRepository;
import com.ninng.es.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 16:53
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public UserServiceImpl(UserRepository userRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.userRepository = userRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> byId = userRepository.findById(id);
        elasticsearchRestTemplate.get(id, User.class, IndexCoordinates.of("user"));
        byId.ifPresent(u -> log.info("User" + u));
        return byId;
    }
}
