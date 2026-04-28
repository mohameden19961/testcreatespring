# Mon Projet Spring Boot

## Description
Ce projet est une application backend développée avec Spring Boot pour la gestion de produits. Il implémente une API REST sécurisée avec une architecture en couches pour assurer la maintenabilité et la scalabilité.

## Technologies Utilisées
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Security (Authentification Basic)
- MySQL
- Lombok
- Maven

## Structure du Projet
L'application est structurée de la manière suivante :
- controllers : Gère les requêtes HTTP entrantes et définit les endpoints.
- services : Contient la logique métier et fait le lien entre les contrôleurs et les dépôts.
- data.entities : Définit les entités persistantes pour MySQL.
- data.repositories : Gère les interactions avec la base de données via Spring Data JPA.
- dto : Contient les objets de transfert de données (Data Transfer Objects).
- security : Gère la configuration de la sécurité, le hachage des mots de passe et le service de détails utilisateur.
- exceptions : Fournit un mécanisme global de gestion des erreurs pour des réponses API cohérentes.

## Installation et Configuration

### Prérequis
- Java JDK 21
- Maven 3.x
- Serveur MySQL

### Base de données
1. Créez une base de données MySQL nommée : testprojetspring
2. Vérifiez ou modifiez les identifiants dans src/main/resources/application.properties :
   - spring.datasource.username=supnum
   - spring.datasource.password=Supnum

### Exécution
Pour lancer l'application en local :
```bash
mvn spring-boot:run
```

## Points d'Entrée de l'API (Endpoints)

### Gestion des Produits
- GET /api/products : Liste tous les produits (Accès : Public)
- GET /api/products/{id} : Détails d'un produit spécifique (Accès : ROLE_ADMIN)
- POST /api/products : Ajouter un nouveau produit (Accès : Authentifié)
- PUT /api/products/{id} : Modifier un produit existant (Accès : Authentifié)
- DELETE /api/products/{id} : Supprimer un produit (Accès : ROLE_ADMIN)

### Gestion des Utilisateurs
- POST /api/users/register : Créer un nouveau compte utilisateur (Accès : Public)

## Format des Réponses
L'API renvoie systématiquement un objet structuré pour faciliter l'intégration :
- message : Description textuelle de l'opération.
- success : Booléen indiquant si l'opération a réussi.
- data : Les données demandées ou le résultat de l'action.

## Sécurité
- Authentification : HTTP Basic Auth.
- Hachage : Les mots de passe sont sécurisés avec BCryptPasswordEncoder.
- CSRF : Désactivé pour le développement de l'API.
