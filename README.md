# monitoring_in_action
- [outline](#outline)
- [diagram](#diagram)

---
# outline

### pull_cluster_data : 프로메테우스 데이터를 수집
- use Prometheus, kotlin
- Control incoming information from the Kotlin language using the rancher tool

### management_data_calc : 수집된 데이터 기반 리소스 예측/ 제시 인공지능 모듈
- use mongoDB, pyhon

### (미개발) db_info_exporter: Pod Exporter
- use oracle21c, go
- build a docker deployment
- SOURCE : expected

### (미개발) management_broker
- use docker controller

### (미개발) container_info_exporter
- use traefik, docker swarm, prometheus
- build a docker deployment
- SOURCE : expected

### (미개발) management_data
- use springboot or golang or python
- create data processing load balancing logic based on input information
- keep server management constant by passing commands through declarative API configuration
- building a management system using chatGPT
- build a docker deployment
- SOURCE : expected

---
# diagram
![이미지제목](/diagram_20230131.png)

---

### used skill
- sharding : org.apache.shardingsphere:sharding-jdbc-core:4.1.1'

### default setup
- docker swarm init
- docker network rm ingress
- docker network create --ingress --driver overlay --opt encrypted --subnet 10.10.0.0./16 ingress
- docker network create --subnet 10.11.0.0/16 --driver overlay --scope swarm --opt encrypted --attachable cloud-edge
- docker network create --subnet 10.12.0.0/16 --driver overlay --scope swarm --opt encrypted --attachable cloud-socket-proxy
- docker network create --subnet 10.13.0.0/16 --driver overlay --scope swarm --opt encrypted --attachable cloud-public
- export NODE_ID=$(docker info -f '{{.Swarm.NodeID}}')
- docker node update --label-add cloud-public.traefik-certificates=true $NODE_ID
- sudo apt update && sudo apt install -y apache2-utils
- export DOMAIN="<domain here>"
- export EMAIL="<email for letsencrypt certificates here>"
- export WHITELIST_IP="<your public ip>/32"
- export USERNAME="<username here>"
- export TRAEFIK_ADMINS=$(htpasswd -nBC 10 $USERNAME)

### example plan
- Golang exporter
- Docker swarm과 traefik
- Kafka spring boot 예제
