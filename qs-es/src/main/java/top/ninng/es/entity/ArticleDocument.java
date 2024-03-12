package top.ninng.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author OhmLaw
 * @Date 2023/2/21 9:27
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "article")
public class ArticleDocument implements Serializable {

    private static final long serialVersionUID = 2851023367114404419L;

    @Id
    String id;
    @Field(name = "user_id", type = FieldType.Keyword)
    String userId;
    @Field(name = "title", type = FieldType.Text, analyzer = "ik_max_word")
    String title;
    @Field(name = "content", type = FieldType.Text, analyzer = "ik_max_word")
    String content;
    @Field(name = "create_time", type = FieldType.Date)
    Date createTime;
    @Field(name = "update_time", type = FieldType.Date)
    Date updateTime;
    long count;
}
