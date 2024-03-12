package top.ninng.es.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.ninng.es.entity.User;

import java.util.Optional;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 16:48
 * @Version 1.0
 */
public interface UserRepository extends ElasticsearchRepository<User, String> {

    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(String id);

    @Override
    Page<User> searchSimilar(User entity, String[] fields, Pageable pageable);
}
