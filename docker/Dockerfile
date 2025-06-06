FROM eclipse-temurin:21-jdk-jammy

RUN apt-get update --fix-missing && \
    apt-get install -y python3-pip && \
    pip install supervisor

WORKDIR /app

COPY . .

COPY docker/supervisord.conf /etc/supervisord.conf

RUN chmod +x ./gradlew

CMD ["supervisord", "-c", "/etc/supervisord.conf"]