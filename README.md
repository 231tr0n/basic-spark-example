# basic-spark-example
Sample spark setup with one master and two slaves.

## Running the cluster
```bash
docker-compose up -d
```

## Building the jar
```bash
mvn clean install
```
> [!NOTE]
> Use java 17 only

## Running the jar
```bash
spark-submit --class com.example.Hello target/test-1.0-SNAPSHOT.jar --master spark://localhost:7077
```

## Monitoring the spark job
- Open firefox in the browser by navigating to `http://localhost:5800`.
- Open the Spark UI in the embeded browser by opening the following URL `http://spark:8080` to monitor the jobs.
