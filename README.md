# DIPcom-simulator

Simulátor pro demonstrační nebo testovací účely, postavený na Spring Boot a běžící v Docker prostředí.

## Použité technologie

- **Java 21** – běhové prostředí
- **Gradle 8.13** – běhové prostředí
- **Spring Boot** – backend aplikace
- **PostgreSQL** – relační databáze
- **Redis** – cache/message broker
- **Docker & Docker Compose** – orchestrace běhového prostředí

## Spuštění projektu

> Předpoklady: nainstalovaný Docker a Docker Compose

```bash
docker-compose up --build
```
