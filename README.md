# monitoring_in_action
- [outline](#outline)
- [diagram](#diagram)

---
# outline

### db_info_exporter
- use mariadb, go

### container_info_exporter
- use traefik, docker swarm, prometheus

### management_data
- use springboot
- create data processing load balancing logic based on input information
- keep server management constant by passing commands through declarative API configuration

### management_model
- use sklearn
- building a model to configure automatic server management: time series data pattern analysis

### scraping_example
- use selenium springboot

---
# diagram
![이미지제목](/diagram_20230130.png)

---

### used skill
- sharding : org.apache.shardingsphere:sharding-jdbc-core:4.1.1'
