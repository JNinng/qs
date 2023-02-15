package top.ninng.es.service;

import top.ninng.es.constants.ConsumerConstants;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 13:31
 * @Version 1.0
 */
@Slf4j
@Service
public class IndexDatabaseService {

    private static final String TAG = "IndexDatabaseService";
    private RestHighLevelClient client;

    public IndexDatabaseService(RestHighLevelClient client) {
        this.client = client;
    }

    public void createIndexDatabase(String index) {
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.source(ConsumerConstants.CONSUMER_MAPPING_TEMPLATE, XContentType.JSON);
        try {
            client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }

    public void getDocumentById(String id) {
        GetRequest request = new GetRequest("user", id);
        try {
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            String json = response.getSourceAsString();
            log.info("json: " + json);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }
}
