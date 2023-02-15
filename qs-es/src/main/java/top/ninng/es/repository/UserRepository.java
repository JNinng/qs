package top.ninng.es.repository;

import top.ninng.es.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 16:48
 * @Version 1.0
 */
public interface UserRepository extends ElasticsearchRepository<User, String> {

    @Override
    Optional<User> findById(String id);
}
