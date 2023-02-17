package top.ninng.es.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;
import top.ninng.es.entity.User;
import top.ninng.es.repository.UserRepository;
import top.ninng.es.service.UserService;

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

    @Override
    public void save() {
        userRepository.save(new User("1", "John名字"));
        userRepository.save(new User("2", "天涯共此时"));
        userRepository.save(new User("3", "海内存知己天涯若比邻"));
        userRepository.save(new User("4", "半夜睡不着觉把心情哼成歌"));
        userRepository.save(new User("5", "半夜睡不着觉把心情哼成歌"));
        userRepository.save(new User("6", "半夜睡不着觉把心情哼成歌"));
        userRepository.save(new User("7", "半夜睡不着觉把心情哼成歌"));
        userRepository.save(new User("8", "半夜睡不着觉把心情哼成歌"));
    }
}
