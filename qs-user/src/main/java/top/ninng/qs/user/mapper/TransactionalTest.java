package top.ninng.qs.user.mapper;

import org.springframework.stereotype.Repository;

/**
 * @Author OhmLaw
 * @Date 2022/9/19 14:34
 * @Version 1.0
 */
@Repository("transactionalTest")
public interface TransactionalTest {

    /**
     * 修改测试值
     *
     * @param id
     * @param value
     * @return
     */
    int update(long id, long value);
}
