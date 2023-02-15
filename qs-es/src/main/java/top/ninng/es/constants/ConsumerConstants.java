package top.ninng.es.constants;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 13:33
 * @Version 1.0
 */
public class ConsumerConstants {

    public static final String CONSUMER_MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"user_name\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
