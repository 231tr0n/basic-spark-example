# basic-spark-example
Sample spark setup with one master and two slaves.

## Running the cluster
```bash
docker-compose up -d
```

## Running the jar
```bash
spark-submit --class com.example.Hello target/test-1.0-SNAPSHOT.jar --master spark://localhost:7077
```
