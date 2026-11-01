# Task Link Community Micro Task Platform | MID Term Exam

## Introduction

- **Student Name:** MANZI Kennedy
- **Student ID:** 27600
- **Course Name:** Web Technology and Internet
- **Course ID:** INSY 8322
- **Instrucor:** DUSHIMIMANA Patrick


## [Project](https://docs.google.com/document/d/1j0EbY2dznWdqncYwqLcW6w80wKJFvIHZ/edit)

Task Link Community Micro Task Platform is a Spring Boot REST API for managing posters, helpers, zones, skills, tasks, ratings, and dashboard reports.

## Requirements

- Java 17
- Maven Spring boot
- PostgreSQL
- Postman

## Run the API

1. Create a PostgreSQL database named `tasklink`.
2. Open `src/main/resources/application.properties` and configure:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/tasklink
   spring.datasource.username=postgres
   spring.datasource.password=YOUR_PASSWORD
   ```

3. From the project directory, start the API:

   ```powershell
   .\mvn spring-boot:run
   ```

4. The API runs on `http://localhost:8085`.

## Postman Test Report

### Base URL

```text
http://localhost:8085/api/{path}/{actions}
```

### Domain CRUD

#### Create a zone

```http
POST /api/zones/save
Content-Type: application/json
```

```json
{
  "zoneNumber": 1,
  "name": "Kigali Central"
}
```

#### Create a helper

```http
POST /api/helpers/save
Content-Type: application/json
```

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "phone": "0780000001",
  "zone": {
    "id": "ZONE_UUID"
  }
}
```

#### Create a skill

```http
POST /api/skills/save
Content-Type: application/json
```

```json
{
  "name": "Plumbing"
}
```

#### Create a poster

```http
POST /api/posters/save
Content-Type: application/json
```

```json
{
  "firstName": "Alice",
  "lastName": "Smith",
  "username": "alice",
  "phone": "0780000002"
}
```

#### Create a task

```http
POST /api/tasks/save
Content-Type: application/json
```

```json
{
  "title": "Repair kitchen tap",
  "location": "Kigali",
  "taskDate": "2026-11-05",
  "poster": {
    "id": "POSTER_UUID"
  },
  "helper": {
    "id": "HELPER_UUID"
  }
}
```

Successful create requests return the appropriate response with HTTP `201 Created`.

### Derived Queries

```http
GET /api/posters/by-last-name?lastName=Smith
GET /api/tasks/by-status?status=DONE
GET /api/tasks/between?start=2026-11-01&end=2026-11-30
GET /api/helpers/by-skill?name=Plumbing
GET /api/skills/unused
GET /api/zones/free
GET /api/posters/of-helper/HELPER_UUID
```

Each request was tested in Postman and returns HTTP `200 OK`.

### JPQL Queries

```http
GET /api/helpers/by-skill?name=Plumbing
GET /api/posters/of-helper/HELPER_UUID
GET /api/skills/unused
GET /api/zones/free
GET /api/ratings/poster/alice
```

The entity relationship fields were used in the JPQL queries, while collection relations were accessed through the required joins.

### Business Rules

#### Add a skill to a helper

```http
PUT /api/helpers/HELPER_UUID/skills/SKILL_UUID
```

Adding the same skill again returns HTTP `409 Conflict`.

#### Change a helper's zone

```http
PATCH /api/helpers/HELPER_UUID/zone?zoneNumber=2
```

The zone belongs to only one helper. Assigning an occupied zone returns HTTP `409 Conflict`.

#### Change task status

```http
PATCH /api/tasks/TASK_UUID/status?status=CLAIMED
PATCH /api/tasks/TASK_UUID/status?status=DONE
```

Allowed transitions are:

- `OPEN` to `CLAIMED` or `CANCELLED`
- `CLAIMED` to `DONE` or `CANCELLED`

An invalid transition returns HTTP `400 Bad Request`.

### Ratings

#### Create a rating

```http
POST /api/ratings/save?taskId=TASK_UUID
Content-Type: application/json
```

```json
{
  "reviewer": "alice",
  "comment": "Excellent work",
  "score": 5
}
```

Ratings are accepted only for tasks with `DONE` status. A rating on a task in another status returns HTTP `400 Bad Request`.

#### Query ratings by poster

```http
GET /api/ratings/poster/alice
```

The response includes the task and its related poster and helper.

#### Delete a rating

```http
DELETE /api/ratings/delete/RATING_UUID
```

Deleting a rating with an unknown ID returns HTTP `404 Not Found`.

### Reports and Bulk Update

#### Task count by status

```http
GET /api/tasks/stats/by-status
```

#### Cancel tasks for a helper on a date

```http
PATCH /api/tasks/cancel-day?helperId=HELPER_UUID&date=2026-11-05
```

The operation cancels matching non-`DONE` tasks and returns the number of cancelled tasks.

#### Dashboard summary

```http
GET /api/dashboard/summary
```

#### Task pagination

```http
GET /api/tasks/page?page=0&size=5&sort=taskDate,asc
```

### Failing Cases Demonstrated

| Case | Request | Expected result |
|---|---|---|
| Past task date | `POST /api/tasks/save` with `taskDate: "2020-01-01"` | `400 Bad Request` |
| Invalid status transition | Changing a task directly from `OPEN` to `DONE` | `400 Bad Request` |
| Existing skill | Adding a skill already assigned to the helper | `409 Conflict` |
| Taken zone | Assigning a zone owned by another helper | `409 Conflict` |
| Unknown task | `GET /api/tasks/00000000-0000-0000-0000-000000000000` | `404 Not Found` |
