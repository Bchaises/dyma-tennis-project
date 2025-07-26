# 🎾 Dyma Tennis Project

Bienvenue dans le projet **Dyma Tennis**, réalisé dans le cadre de la formation Spring Boot avec Java proposée par Dyma. Ce projet a pour but de mettre en pratique les concepts de développement d'une API REST avec Spring Boot.

## 🚀 Objectifs pédagogiques

- Comprendre la structure d’un projet Spring Boot.
- Créer et exposer des endpoints REST.
- Gérer des entités JPA et les relations entre elles.
- Mettre en place la persistance des données avec Spring Data JPA.
- Tester les fonctionnalités avec Insomnia ou Swagger.

## 🛠️ Technologies utilisées

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- MariaDB
- Lombok
- Maven

## 📁 Structure du projet
dyma-tennis-project/   
├── src/  
│ ├── main/  
│ │ ├── java/  
│ │ │ └── com/dyma/tennis/  
│ │ │ ├── controller/  
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
   git clone https://github.com/<ton-utilisateur>/dyma-tennis-project.git
   cd dyma-tennis-project

2. Lancer l'application depuis un IDE ou en ligne de commande :
``` bash
./mvnw spring-boot:run
```
3. L'API sera accessible à l'adresse :
   http://localhost:8080

## 🔍 Fonctionnalités de l'API

- Gestion des joueurs (/joueurs)
- Gestion des matchs (/matchs)
- Gestion des tournois (/tournois)
- Possibilité de créer, lire, mettre à jour, et supprimer les entités (CRUD)

> Les routes exactes sont documentées dans Swagger (si intégré) ou peuvent être testées avec Postman/Insomnia.

## 🧪 Tests

Les endpoints peuvent être testés à l’aide de :
- Swagger UI (optionnel)
- Postman/Insomnia
- curl
- ou toute autre interface HTTP

## 💡 À propos

Ce projet est à visée pédagogique. Il permet d'appréhender de manière concrète le développement d'une API REST avec Spring Boot, en appliquant les bonnes pratiques du développement backend moderne.

---

**Auteur** : Benjamin CHAISES  
**Formation suivie** : https://dyma.fr/formations/spring-boot