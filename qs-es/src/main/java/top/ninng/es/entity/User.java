package top.ninng.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 16:42
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@Document(indexName = "user")
public class User implements Serializable {

    @Id
    String id;
    @Field(name = "user_name", type = FieldType.Text, analyzer = "ik_max_word")
    String userName;
}
