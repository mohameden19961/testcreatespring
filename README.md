# testcreatespring 🚀

Une application Spring Boot robuste et professionnelle pour la gestion des étudiants et des cours, intégrant une authentification sécurisée par JWT et des relations complexes Many-to-Many.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## 📋 Description

Ce projet est une API REST développée avec **Spring Boot** qui permet de gérer un système éducatif. Il met en œuvre les meilleures pratiques de développement Java, incluant la sécurité, la validation des données et une architecture en couches (Controller, Service, Repository).

## 🛠 Stack Technique

- **Langage :** Java 21
- **Framework :** Spring Boot 3.4.5
- **Sécurité :** Spring Security & JWT (Json Web Token)
- **Persistance :** Spring Data JPA / Hibernate
- **Base de données :** MySQL
- **Validation :** Hibernate Validator
- **Utilitaires :** Lombok, Maven

## 📂 Structure du Projet

```text
src/main/java/com/example/monprojet/
├── controllers/    # Points d'entrée de l'API (REST Controllers)
├── services/       # Logique métier
├── data/
│   ├── entities/   # Modèles de données JPA (Student, Course, User)
│   └── repositories/# Interfaces de communication avec la BDD
├── security/       # Configuration JWT et Spring Security
├── dto/            # Objets de transfert de données
└── exceptions/     # Gestion centralisée des erreurs
```

## 🚀 Installation et Démarrage

### Prérequis

- JDK 21 ou supérieur
- Maven 3.8+
- MySQL Server

### Étapes

1. **Cloner le projet**
   ```bash
   git clone https://github.com/mohameden19961/testcreatespring.git
   cd testcreatespring
   ```

2. **Configurer la base de données**
   Modifiez le fichier `src/main/resources/application.properties` :
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/votre_db
   spring.datasource.username=votre_utilisateur
   spring.datasource.password=votre_mot_de_passe
   ```

3. **Compiler et exécuter**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 🔌 API Endpoints

### Authentification
- `POST /api/users/register` : Créer un nouveau compte
- `POST /api/users/login` : Se connecter et recevoir un token JWT

### Étudiants
- `GET /api/students` : Liste tous les étudiants
- `POST /api/students` : Ajouter un étudiant
- `GET /api/students/{id}` : Détails d'un étudiant

### Cours
- `GET /api/courses` : Liste tous les cours
- `POST /api/courses` : Créer un nouveau cours

## 🤝 Contribution

Les contributions sont les bienvenues ! Consultez le fichier [CONTRIBUTING.md](CONTRIBUTING.md) pour plus d'informations.

## 📄 Licence

Distribué sous la licence MIT. Voir `LICENSE` pour plus d'informations.

---
Développé avec ❤️ par [mohameden19961](https://github.com/mohameden19961)
