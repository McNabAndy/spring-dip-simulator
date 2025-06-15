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
## Nastavení importu dat do databáze
V `supervisord.conf` je aktuálně při spuštění nastaven argument `--import=true` který zajistí import dat do databáze.



## API
K testovaní API lze využít kolekci Postmana ve složce [doc/postman/](doc/postman)

GET - zobrazení detailu knihy dle Object_id
```
api/books/{objecId}
```



GET - zobrazení výpisu seznamu detailů knih, možnost paginace. Při absenci parametrů je defultně nastaveno 5 záznamů na stránku.
```
api/books?page=0&size=5
```



POST - vytvoření nového záznamu.
```
api/books
```


DELETE - smazání záznamu dle Object_id
```
api/books/{objecId}
```
