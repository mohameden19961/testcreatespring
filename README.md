# Mon Projet Spring Boot - Gestion des Étudiants (API REST)

Note : Ce projet est un programme d'apprentissage sur Spring Boot, réalisé par un débutant. Il sert à explorer les bases de la création d'une API REST, de la sécurité et de la gestion des bases de données. Il a été mis à jour pour suivre le TP de SUPNUM Mauritanie.

## Description
L'application est un système de gestion d'étudiants et de leurs cours avec une architecture en couches. Elle utilise Spring Security (JWT) pour protéger les données et restreindre les actions selon le rôle de l'utilisateur.

## Mise à jour du système
Le système a été migré de la gestion de produits vers la gestion d'étudiants (`Student`) et de cours (`Course`).

### Obligation de Configuration de l'Administrateur
Il est obligatoire de configurer vos propres identifiants d'administrateur avant de lancer l'application. Pour cela, modifiez le fichier suivant :
`src/main/java/com/example/monprojet/DataInitializer.java`

Identifiants par défaut (à modifier) :
- **Email** : `24068@supnum.mr`
- **Mot de passe** : `24068@PASSWORD`

### Logique des Rôles
- **Inscription** : Tout nouvel utilisateur inscrit via l'API publique reçoit automatiquement le rôle `USER`.
- **Rôle ADMIN** : Seul l'administrateur peut créer, modifier ou supprimer des étudiants et des cours.
- **Rôle USER / CLIENT** : Peut consulter les détails d'un étudiant.

### Modèle de Données (MCD)
Le système gère une relation entre les étudiants et les cours :
- **Un étudiant peut avoir plusieurs cours** (Relation OneToMany).
- **Chaque cours est lié à un étudiant unique**.

## Technologies
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA (MySQL)
- Spring Security (JWT + BCrypt)
- Lombok
- Maven

## Structure du projet
- `controllers` : Points d'entrée de l'API REST.
- `services` : Logique métier (traitement des données, conversion DTO).
- `data.entities` : Modèles JPA pour la base de données.
- `data.repositories` : Interfaces Spring Data JPA.
- `dto` : Objets de transfert de données (StudentDTO, CourseDTO).
- `security` : Configuration JWT et sécurité.
- `exceptions` : Gestion globale des erreurs.

## Endpoints de l'API

### Gestion des Étudiants
- **GET** `/api/students` : Liste tous les étudiants. *Accès : Public.*
- **GET** `/api/students/page?page=0&size=10` : Liste paginée des étudiants. *Accès : Public.*
- **GET** `/api/students/{id}` : Détails d'un étudiant (inclut ses cours). *Accès : Public.*
- **GET** `/api/students/search?name=...` : Recherche par nom. *Accès : Public.*
- **POST** `/api/students` : Créer un étudiant. *Accès : ADMIN.*
- **PUT** `/api/students/{id}` : Modifier un étudiant. *Accès : ADMIN.*
- **DELETE** `/api/students/{id}` : Supprimer un étudiant. *Accès : ADMIN.*

### Gestion des Cours
- **GET** `/api/courses` : Liste tous les cours existants. *Accès : Public.*
- **POST** `/api/courses/student/{studentId}` : Ajouter un nouveau cours à un étudiant. *Accès : ADMIN.*

### Gestion des Utilisateurs (Sécurité)
- **POST** `/api/users/register` : Enregistrer un nouvel utilisateur (Rôle USER). *Accès : Public.*
- **POST** `/api/users/login` : Connexion par email. Retourne un **Token JWT**. *Accès : Public.*
- **PUT** `/api/users/{id}/role` : Changer le rôle d'un utilisateur. *Accès : ADMIN.*

## Configuration
1. Créer une base de données MySQL nommée : `testprojetspring`
2. Configurer vos identifiants dans `src/main/resources/application.properties`.
3. Lancer l'application : `mvn spring-boot:run`
4. L'application est accessible sur : `http://localhost:8081`

## Sécurité (JWT)
Pour accéder aux endpoints protégés (ex: création d'étudiant) :
1. Se connecter via `POST /api/users/login` pour obtenir le token.
2. Ajouter le token dans l'en-tête de vos requêtes : `Authorization: Bearer <votre_token>`.
