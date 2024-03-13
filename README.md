# QS

QS 系列项目：微服务架构的用户社区和独立博客的交互例。

## 外部服务于技术选项

### 服务

| Service              | Port                     |      |
| -------------------- | ------------------------ | ---- |
| Nacos 2.1.1          | 8848（默认）             | 7848 |
| Sentinel 1.8.5       | 8080（默认）             | 7080 |
| Seata 1.5.2          | 7091（默认）             |      |
| Elasticsearch 7.12.1 | 9200（默认）             | 7200 |
| Kibana 7.12.1        | 5601（默认）             |      |
| Logstash 7.12.1      | 5044（json_lines,debug） |      |
| Redis 3.2.100        | 6379（默认）             |      |
| MySQL 5.7.28         | 3306（默认）             |      |

### 其他选型

- 微服务框架：SpringCloud
- 消息队列：RabbitMQ
- 数据库连接池：Druid
- 持久层框架：MyBatis
- Web 服务客户端：Openfeign
