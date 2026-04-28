# Mon Projet Spring Boot - Apprentissage

> [!NOTE]
> **Avertissement** : Ce projet n'est pas un produit fini. Il s'agit d'un **programme d'apprentissage sur Spring Boot**, réalisé par un **débutant** dans le cadre de sa formation. L'objectif est d'explorer les concepts fondamentaux de Spring, de la sécurité et de la persistance des données.

## Description
Ce projet est une application backend développée avec Spring Boot pour la gestion de produits. Il implémente une API REST sécurisée avec une architecture en couches pour assurer la maintenabilité et la scalabilité.

## Dernières Mises à Jour
- **Initialisation Automatique** : Le système crée désormais un compte administrateur par défaut au premier démarrage.
- **Gestion des Rôles** : Mise en place d'une logique où seul l'administrateur système peut attribuer ou modifier les rôles des utilisateurs.
- **Sécurisation de l'Inscription** : Par défaut, tout nouvel utilisateur inscrit via l'API publique reçoit le rôle `USER`.

## Identifiants Administrateur (Système)
Au démarrage, le compte suivant est créé automatiquement :
- **Nom** : Abdy Mohameden
- **Email** : 24068@supnum.mr
- **Mot de passe** : 24068@PASSWORD
- **Rôle** : ADMIN

## Technologies Utilisées
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Security (Authentification Basic + BCrypt)
- MySQL
- Lombok
- Maven

## Structure du Projet
L'application est structurée de la manière suivante :
- **controllers** : Gère les requêtes HTTP entrantes et définit les endpoints.
- **services** : Contient la logique métier et fait le lien entre les contrôleurs et les dépôts.
- **data.entities** : Définit les entités persistantes pour MySQL.
- **data.repositories** : Gère les interactions avec la base de données via Spring Data JPA.
- **dto** : Contient les objets de transfert de données (Data Transfer Objects).
- **security** : Gère la configuration de la sécurité, le hachage des mots de passe et le service de détails utilisateur.
- **exceptions** : Fournit un mécanisme global de gestion des erreurs pour des réponses API cohérentes.
- **DataInitializer.java** : Gère l'initialisation des données au démarrage (Admin par défaut).

## Installation et Configuration

### Prérequis
- Java JDK 21
- Maven 3.x
- Serveur MySQL

### Base de données
1. Créez une base de données MySQL nommée : `testprojetspring`
2. Vérifiez ou modifiez les identifiants dans `src/main/resources/application.properties`.

### Exécution
Pour lancer l'application en local :
```bash
mvn spring-boot:run
```

## Points d'Entrée de l'API (Endpoints)

### Gestion des Produits
- `GET /api/products` : Liste tous les produits (Accès : Public)
- `GET /api/products/{id}` : Détails d'un produit spécifique (Accès : ROLE_ADMIN)
- `POST /api/products` : Ajouter un nouveau produit (Accès : Authentifié)
- `PUT /api/products/{id}` : Modifier un produit existant (Accès : Authentifié)
- `DELETE /api/products/{id}` : Supprimer un produit (Accès : ROLE_ADMIN)

### Gestion des Utilisateurs
- `POST /api/users/register` : Créer un nouveau compte (Rôle par défaut : USER)
- `POST /api/users/login` : Authentification utilisateur.
- `POST /api/users/{id}/role` : Modifier le rôle d'un utilisateur (Accès : **ROLE_ADMIN uniquement**)

## Sécurité
- **Authentification** : HTTP Basic Auth.
- **Hachage** : Les mots de passe sont sécurisés avec `BCryptPasswordEncoder`.
- **RBAC (Role Based Access Control)** : Accès restreint basé sur les rôles (USER, ADMIN).
