# Système de Gestion des Étudiants et Cours (API REST)

Ce projet est une application de gestion académique développée dans le cadre du TP de Spring Boot à **SUPNUM Mauritanie**. Il démontre l'utilisation des technologies modernes de Spring pour créer une API REST sécurisée et performante.

## 🚀 Fonctionnalités principales

- **Gestion des Étudiants** : CRUD complet (Création, Lecture, Mise à jour, Suppression).
- **Gestion des Cours** : CRUD complet pour les matières/cours.
- **Système d'Inscriptions (Bonus)** : Relation **ManyToMany** permettant d'inscrire plusieurs étudiants à plusieurs cours.
- **Sécurité Avancée** : Authentification via **JWT (JSON Web Token)** et gestion des rôles (**ADMIN** et **USER**).
- **Pagination & Recherche** : Liste paginée des étudiants et recherche par nom.
- **Gestion Globale des Erreurs** : Réponses API standardisées et gestion des exceptions.

## 🏗️ Architecture & Technologies

L'application suit une architecture en couches standard :
- **Java 21** & **Spring Boot 3.4.5**
- **Spring Security** : JWT + BCrypt pour le hachage des mots de passe.
- **Spring Data JPA** : Persistance des données avec MySQL.
- **Lombok** : Pour un code plus concis (Getters, Setters, etc.).
- **Validation** : Jakarta Validation pour l'intégrité des données entrantes.

## 🔑 Sécurité et Accès

Le système utilise deux niveaux d'accès :
1. **Administrateur (ADMIN)** : Accès complet (Gestion des étudiants, création de cours, modification des rôles).
2. **Étudiant (USER)** : Accès en lecture et possibilité de **s'inscrire à des cours**.

> [!IMPORTANT]
> Les identifiants de l'administrateur par défaut sont configurés dans la classe `DataInitializer.java`. Veuillez les modifier avant le déploiement pour assurer la sécurité de votre application.

## 📡 Endpoints principaux

### Authentification
- `POST /api/users/register` : Créer un compte (Rôle USER par défaut).
- `POST /api/users/login` : Se connecter et obtenir un token JWT.

### Étudiants & Inscriptions
- `GET /api/students` : Liste de tous les étudiants.
- `GET /api/students/page` : Liste paginée (ex: `?page=0&size=10`).
- `GET /api/students/search?name=...` : Recherche par nom.
- `POST /api/students/{id}/enroll/{courseId}` : Inscrire un étudiant à un cours.
- `POST /api/students` : Créer un étudiant (**ADMIN**).

### Cours
- `GET /api/courses` : Liste des cours disponibles.
- `POST /api/courses` : Créer un nouveau cours (**ADMIN**).
- `DELETE /api/courses/{id}` : Supprimer un cours (**ADMIN**).

## 🛠️ Installation

1. Cloner le projet.
2. Créer une base de données MySQL : `testprojetspring`.
3. Configurer vos accès (DB_USERNAME, DB_PASSWORD) dans `src/main/resources/application.properties`.
4. Lancer l'application :
   ```bash
   mvn spring-boot:run
   ```
5. Accéder à l'API sur `http://localhost:8081`.

---
*Réalisé dans le cadre du TP Spring Boot API REST - SUPNUM Mauritanie.*
