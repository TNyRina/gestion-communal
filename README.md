# Gestion Communale - API REST

## Description du projet

Gestion Communale est une application REST développée avec **Spring Boot** permettant la gestion des données d'une commune et des actes d'état civil.

Le projet permet notamment de gérer :

* Les communes
* Les fokontany
* Les citoyens
* Les agents comnunales
* Les actes de naissance
* Les actes de décès
* Les actes de mariage

L'application expose une API REST permettant la création, la modification, la suppression et la consultation des différentes ressources.

---

# Technologies utilisées

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* Gradle
* Base de données H2 (modifiable)
* REST API

---

# Configuration

## Configuration de la base de données

Le projet utilise par défaut une base H2.

Exemple de configuration dans `application.properties` :

```properties
spring.datasource.url=jdbc:h2:mem:gestion_commune
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Vous pouvez remplacer cette configuration par MySQL, PostgreSQL ou toute autre base compatible avec Spring Data JPA.

---

# Lancement du projet

## Avec Gradle

```bash
./gradlew bootRun
```

Sous Windows :

```bash
gradlew.bat bootRun
```

Le serveur démarre par défaut sur :

```
http://localhost:8080
```

---

# Vérification de l'API

Les données peuvent être consultées :

* avec un navigateur pour les requêtes GET ;
* avec `curl` ;
* avec Postman ou tout autre client REST.

Exemple :

```
GET http://localhost:8080/api/citoyens
```

ou

```bash
curl http://localhost:8080/api/citoyens
```

---

# Préparation des données de test

Avant de tester les actes d'état civil, il est nécessaire d'ajouter quelques données.

L'ordre suivant est recommandé afin de respecter les dépendances entre les entités :

1. Commune
2. Fokontany
3. Citoyens
4. Officier d'état civil
5. Actes de naissance
6. Actes de décès
7. Actes de mariage

Il est recommandé de créer au minimum :

* 1 Commune
* 1 Fokontany
* 1 Officier d'état civil
* 4 ou 5 Citoyens

---

# 1. Ajouter une Commune

```bash
curl -X POST http://localhost:8080/api/communes \
-H "Content-Type: application/json" \
-d '{
    "nom": "Commune Urbaine d Antananarivo",
    "code": "C101"
}'
```

---

# 2. Ajouter les Fokontany

```bash
curl -X POST http://localhost:8080/api/fokontany/bulk \
-H "Content-Type: application/json" \
-d '[
    {
        "nom": "Ambohimanarina",
        "code": "FKT001",
        "communeId": 1
    },
    {
        "nom": "Analakely",
        "code": "FKT002",
        "communeId": 1
    },
    {
        "nom": "Isotry",
        "code": "FKT003",
        "communeId": 1
    },
    {
        "nom": "Anosizato",
        "code": "FKT004",
        "communeId": 1
    },
    {
        "nom": "Andraharo",
        "code": "FKT005",
        "communeId": 1
    },
    {
        "nom": "Ambohipo",
        "code": "FKT006",
        "communeId": 1
    },
    {
        "nom": "Itaosy",
        "code": "FKT007",
        "communeId": 1
    },
    {
        "nom": "67 Ha",
        "code": "FKT008",
        "communeId": 1
    },
    {
        "nom": "Ambatomainty",
        "code": "FKT009",
        "communeId": 1
    },
    {
        "nom": "Tsaralalana",
        "code": "FKT010",
        "communeId": 1
    }
]'
```

---

# 3. Ajouter les citoyens

Créer les quatre citoyens à l'aide des requêtes `curl` fournies ci-dessous.

## Ajouter Citoyen 1:
```bash
curl -X POST http://localhost:8080/api/citoyens \
-H "Content-Type: application/json" \
-d '{
    "nom": "RAKOTO",
    "prenom": "Jean",
    "numeroCIN": "101001234567",
    "adresse": "ATM 23 bis",
    "dateNaissance": "1998-05-20",
    "lieuNaissance": "Antananarivo",
    "sexe": "MASCULIN",
    "profession": "Developpeur",
    "situationFamiliale": "CELIBATAIRE",
    "fokontanyId": 1
}'
```


## Ajouter Citoyen 2:
```bash
curl -X POST http://localhost:8080/api/citoyens \
-H "Content-Type: application/json" \
-d '{
    "nom": "RASOA",
    "prenom": "Maria",
    "numeroCIN": "101002234567",
    "adresse": "ATM 23 bis",
    "dateNaissance": "1998-05-20",
    "lieuNaissance": "Antananarivo",
    "sexe": "FEMININ",
    "profession": "Medecin",
    "situationFamiliale": "CELIBATAIRE",
    "fokontanyId": 1
}'
```

## Ajouter Citoyen 3:
``` bash
curl -X POST http://localhost:8080/api/citoyens \
-H "Content-Type: application/json" \
-d '{
    "nom": "RAKOTOSOA",
    "prenom": "Marinette",
    "numeroCIN": "101002234517",
    "adresse": "ATM 23 bis",
    "dateNaissance": "1998-05-20",
    "lieuNaissance": "Antananarivo",
    "sexe": "FEMININ",
    "profession": "ETUDIANT",
    "situationFamiliale": "CELIBATAIRE",
    "fokontanyId": 1
}'
```

## Ajouter Citoyen 4:
```bash
curl -X POST http://localhost:8080/api/citoyens \
-H "Content-Type: application/json" \
-d '{
    "nom": "RAZAKA",
    "prenom": "Claude",
    "numeroCIN": "101006234517",
    "adresse": "ATM 23 bis",
    "dateNaissance": "1998-05-20",
    "lieuNaissance": "Antananarivo",
    "sexe": "MASCULIN",
    "profession": "ELEVEUR",
    "situationFamiliale": "CELIBATAIRE",
    "fokontanyId": 1
}'
```

Ces citoyens seront ensuite utilisés pour les différents scénarios de tests.

---

# 4. Ajouter un Officier d'état civil

Créer ensuite l'officier d'état civil avec la requête `curl` fournie.
```bash
curl -X POST http://localhost:8080/api/officiers \
-H "Content-Type: application/json" \
-d '{
    "nom": "RAZAFINDRAKOTO",
    "prenom": "Paul",
    "numeroCIN": "101009926543",
    "adresse": "GBN 398-3",
    "dateNaissance": "1985-02-15",
    "lieuNaissance": "Antananarivo",
    "sexe": "MASCULIN",
    "matricule": "OEC001",
    "fonction": "OFFICIER_ETAT_CIVIL",
    "dateEmbauche": "2020-01-10",
    "communeId": 1
}'
```
---

# Scénarios de test

Une fois les données préparées, les scénarios suivants peuvent être exécutés.

## Scénario 1 : Acte de naissance

Créer un acte de naissance reliant :

* une commune ;
* un officier d'état civil ;
* un enfant ;
* son père ;
* sa mère.

```bash
curl -X POST http://localhost:8080/api/actes/naissance \
-H "Content-Type: application/json" \
-d '{
    "numero": "NAIS-2026-000001",
    "dateEtablissement": "2026-07-16",
    "officierEtatId": 5,
    "communeId": 1,
    "enfantId": 1,
    "pereId": 2,
    "mereId": 3
}'
```

---

## Scénario 2 : Acte de décès

Créer un acte de décès.

```bash
curl -X POST http://localhost:8080/api/actes/dece \
-H "Content-Type: application/json" \
-d '{
    "numero": "DECE-2026-000001",
    "dateEtablissement": "2026-07-16",
    "officierEtatId": 5,
    "communeId": 1,
    "deceId": 2
}'
```

---

## Scénario 3 : Acte de mariage

Créer un acte de mariage reliant :

* le mari ;
* la femme ;
* deux témoins ;
* l'officier d'état civil.

```bash
curl -X POST http://localhost:8080/api/actes/mariage \
-H "Content-Type: application/json" \
-d '{
    "numero": "MARIAGE-2026-000001",
    "dateEtablissement": "2026-07-16",
    "officierEtatId": 5,
    "communeId": 1,
    "mariId": 2,
    "femmeId": 3,
    "temoinsId": [1,5]
}'
```

---

# Vérification des données

Toutes les données peuvent être vérifiées avec les endpoints REST.

Exemples :

```text
GET /api/communes
GET /api/fokontany
GET /api/citoyens
GET /api/officiers
GET /api/actes/naissance
GET /api/actes/dece
GET /api/actes/mariage
```

ou avec `curl` :

```bash
curl http://localhost:8080/api/communes

curl http://localhost:8080/api/fokontany

curl http://localhost:8080/api/citoyens

curl http://localhost:8080/api/officiers

curl http://localhost:8080/api/actes/naissance

curl http://localhost:8080/api/actes/dece

curl http://localhost:8080/api/actes/mariage
```

# Perspectives d'amélioration

Le projet constitue une base fonctionnelle pour la gestion communale et des actes d'état civil. Plusieurs améliorations techniques peuvent être envisagées afin de renforcer sa qualité, sa maintenabilité et sa robustesse.

## 1. Centralisation de la gestion des exceptions

Mettre en place une gestion globale des exceptions à l'aide de `@ControllerAdvice` afin d'uniformiser les réponses d'erreur de l'API (404, 400, 500, etc.) et de fournir des messages plus explicites aux clients.

## 2. Validation des données d'entrée

Ajouter la validation des DTO avec Jakarta Bean Validation (`@NotNull`, `@NotBlank`, `@Size`, `@Past`, etc.) et utiliser `@Valid` dans les contrôleurs afin de garantir l'intégrité des données avant leur traitement.

## 3. Journalisation (Logging)

Remplacer les éventuels `System.out.println()` par un framework de journalisation (SLF4J / Logback) afin de faciliter le suivi des traitements, le diagnostic des erreurs et la maintenance de l'application.

## 4. Tests automatisés

Développer des tests unitaires et des tests d'intégration pour les services, les repositories et les contrôleurs afin d'améliorer la fiabilité du projet et de limiter les régressions.

## 5. Pagination et filtrage

Ajouter la pagination, le tri et le filtrage sur les opérations de consultation afin d'améliorer les performances lorsque le volume de données augmente.

