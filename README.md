# Mon Projet Spring Boot - Apprentissage

Note : Ce projet est un programme d'apprentissage sur Spring Boot, realise par un debutant. Il sert a explorer les bases de la creation d'une API REST, de la securite et de la gestion des bases de donnees.

## Description
L'application est un systeme de gestion de produits avec une architecture en couches. Elle utilise Spring Security pour proteger les donnees et restreindre les actions selon le role de l'utilisateur.

## Mise a jour du systeme
Le systeme a ete mis a jour pour inclure une initialisation automatique de l'administrateur et un controle strict des roles.

### Obligation de Configuration de l'Administrateur
Il est obligatoire de configurer vos propres identifiants d'administrateur avant de lancer l'application en production. Pour cela, vous devez modifier le fichier suivant :
src/main/java/com/example/monprojet/DataInitializer.java

Vous devez imperativement modifier ces lignes avec vos informations :
- admin.setUsername("Votre Nom");
- admin.setEmail("votre@email.com");
- admin.setPassword(passwordEncoder.encode("votre_mot_de_passe"));

### Logique des Roles
- Inscription : Tout nouvel utilisateur inscrit via l'API publique recoit automatiquement le role USER.
- Modification de role : Seul l'administrateur systeme peut modifier le role d'un autre utilisateur.

## Technologies
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA (MySQL)
- Spring Security (JWT + BCrypt)
- Lombok
- Maven

## Structure du projet
- controllers : Points d'entree de l'API.
- services : Logique metier de l'application.
- data.entities : Modeles de donnees pour la base de donnees.
- data.repositories : Interfaces pour les operations de base de donnees.
- dto : Objets de transfert de donnees pour les requetes et reponses.
- security : Configuration de la securite et authentification.
- exceptions : Gestion globale des erreurs.

## Endpoints de l'API

### Gestion des Produits
- GET /api/products : Liste tous les produits. Acces : Public.
- GET /api/products/{id} : Recupere les details d'un produit par son ID. Acces : Admin uniquement.
- POST /api/products : Cree un nouveau produit. Acces : Tout utilisateur authentifie.
- PUT /api/products/{id} : Met a jour un produit existant. Acces : Tout utilisateur authentifie.
- DELETE /api/products/{id} : Supprime un produit. Acces : Admin uniquement.

### Gestion des Utilisateurs
- POST /api/users/register : Enregistre un nouvel utilisateur (Role USER par defaut). Acces : Public.
- POST /api/users/login : Authentification par email. Retourne un **JWT Token**. Acces : Public.
- POST /api/users/{id}/role : Change le role d'un utilisateur (ex: passer de USER a ADMIN). Acces : Admin uniquement.

## Configuration
1. Creer une base de donnees MySQL nommee : `testprojetspring`
2. Copier le fichier `src/main/resources/application.properties.example` vers `src/main/resources/application.properties`.
3. Configurer vos identifiants (base de donnees, utilisateur, mot de passe) dans `src/main/resources/application.properties`.
   - *Note : Ce fichier est ignore par Git pour securiser vos informations.*
4. Lancer l'application avec la commande : `mvn spring-boot:run`
   - Par defaut, l'application est accessible sur : `http://localhost:8081`

## Securite
- Les mots de passe sont haches avec BCrypt.
- L'authentification utilise des **JWT (JSON Web Tokens)**.
- Le systeme est **Stateless** (aucune session stockee sur le serveur).
- Pour acceder aux endpoints proteges :
    1. Se connecter via `POST /api/users/login` pour obtenir le token.
    2. Ajouter le token dans l'en-tete de vos requetes : `Authorization: Bearer <votre_token>`.
- La protection CSRF est desactivee pour faciliter les tests de l'API.
