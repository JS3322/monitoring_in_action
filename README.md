# monitoring_in_action
- [outline](#outline)
- [diagram](#diagram)

---
# outline

### db_info_exporter
- use oracle21c, go
- build a docker deployment
- SOURCE : expected

### container_info_exporter
- use traefik, docker swarm, prometheus
- build a docker deployment
- SOURCE : expected

### management_data
- use springboot or golang or python
- create data processing load balancing logic based on input information
- keep server management constant by passing commands through declarative API configuration
- building a management system using chatGPT
- build a docker deployment
- SOURCE : expected 

### management_model
- use sklearn
- building a model to configure automatic server management: time series data pattern analysis
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
