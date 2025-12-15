
# Customer Feedback Microservices System

## Architecture
- Eureka Server (Service Discovery)
- API Gateway (Spring Cloud Gateway)
- User Service
- Feedback Service
- Analytics Service
- PostgreSQL (separate DB per service)

## Run
```bash
docker-compose up --build
```

## Ports
- Eureka: 8761
- Gateway: 8080
- User: 8081
- Feedback: 8082
- Analytics: 8083
