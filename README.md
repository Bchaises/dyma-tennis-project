# 🎾 Dyma Tennis Project

Bienvenue dans le projet **Dyma Tennis**, réalisé dans le cadre de la formation Spring Boot avec Java proposée par Dyma. Ce projet a pour but de mettre en pratique les concepts de développement d'une API REST avec Spring Boot.

## 🚀 Objectifs pédagogiques

- Comprendre la structure d’un projet Spring Boot.
- Créer et exposer des endpoints REST.
- Gérer des entités JPA et les relations entre elles.
- Mettre en place la persistance des données avec Spring Data JPA.
- Sécuriser l'application avec Spring Security.
- Tester les fonctionnalités avec Insomnia ou Swagger.

## 🛠️ Technologies utilisées

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- Spring Security
- MariaDB
- Lombok
- Maven

## 📁 Structure du projet
dyma-tennis-project/   
├── src/  
│ ├── main/  
│ │ ├── java/  
│ │ │ └── com/dyma/tennis/  
│ │ │ ├── rest/  
│ │ │ ├── model/  
│ │ │ ├── repository/  
│ │ │ ├── service/  
│ │ │ └── TennisApplication.java  
│ │ └── resources/  
│ │ ├── application.properties  
│ │ └── data.sql  
├── pom.xml  
└── README.md  

## ⚙️ Lancer l'application

1. Cloner le dépôt :  
```bash
git clone https://github.com/Bchaises/dyma-tennis-project.git  
cd dyma-tennis-project
```
2. Lancer l'application depuis un IDE ou en ligne de commande :
``` bash
./mvnw spring-boot:run
```
ou dans un environnement de production en spécifiant les arguments de la datasource :  
``` bash
./mvnw clean spring-boot:run \
-Dspring-boot.run.profiles=prod \
-Dspring-boot.run.arguments="--spring.datasource.url= --spring.datasource.username= --spring.datasource.password="
```
3. L'API sera accessible à l'adresse : http://localhost:8080

## Mise en production

1. Lancement de la base de données MariaDB dans docker compose :   
```bash
 docker compose -f src/main/docker/prod/mariadb.yml up -d
```
2. Création de l'image docker :   
```bash
docker build -t dyma-tennis-api .
```
3Lancement du container avec l'image et les arguments :   
```bash
docker run --name dyma-tennis -p 8080:8080 \
-e SPRING_DATASOURCE_URL="jdbc:mariadb://dyma-mariadb-production:3306/mariadb" \
-e SPRING_DATASOURCE_USERNAME="mariadb" \
-e SPRING_DATASOURCE_PASSWORD="ftRcy9Nj?gPhHDF" \
 dyma-tennis-api
```

## 🔍 Fonctionnalités de l'API

- Gestion des joueurs `/joueurs`
  - Lister les joueurs
  - Consulter les informations d'un joueur
  - Créer un joueur
  - Modifier les informations d'un joueur
  - Supprimer un joueur
- Gestion des utilisateurs `/users`
  - le profil visiteur : il ne pourra que consulter la liste
  - le profil administrateur : il aura un accès total aux données

> Les routes exactes sont documentées dans Swagger ou peuvent être testées avec Postman/Insomnia.

## 🧪 Tests

Les endpoints peuvent être testés à l’aide de :
- Swagger UI (optionnel)
- Postman/Insomnia
- curl
- ou toute autre interface HTTP

## 📚 Documentation OpenAPI (Swagger)

L'application expose automatiquement une documentation de l'API au format OpenAPI grâce à la dépendance `springdoc-openapi`.

Une fois l'application lancée, tu peux accéder :
- à la documentation brute au format JSON (norme OpenAPI 3) :  
  👉 [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- à l'interface Swagger UI, pour tester les endpoints directement depuis le navigateur :  
  👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## 💡 À propos

Ce projet est à visée pédagogique. Il permet d'appréhender de manière concrète le développement d'une API REST avec Spring Boot, en appliquant les bonnes pratiques du développement backend moderne.

---

**Auteur** : Benjamin CHAISES  
**Formation suivie** : https://dyma.fr/formations/spring-boot