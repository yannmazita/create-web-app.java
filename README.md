# create-web-app.java

Scaffolding to create a web application using Spring Boot and VueJS.

## Running


<details>
    <summary>Basic commands</summary>

The frontend, the backend and the database live in Docker containers. Utility scripts can be found in the `scripts` directory.

To start the development environment, run from the project root:

```commandline
bash ./scripts/run-dev.sh [--options]
```

To start the production environment, run from the project root:

```commandline
bash ./scripts/run-prod.sh [--options]
```

You can specify Docker options like `--build`.
Services can also be started individually with :

```commandline
docker compose up [--options] <docker-service>
```
</details>

The frontend is served to `localhost:5173` in dev and `localhost:80` in prod.
The backend is served to `localhost:8000` in both environments, currently.

However backend services depend on database services, starting the former will start up the latter.

The API (OpenAPI/SwaggerUI) documentation can be found at `localhost:8000/docs#`.

## Infrastructure

<details>
    <summary>How do the docker services look like?</summary>

Two environments (docker profiles) are currently set up, `dev` and `prod`.

Services are setup following this naming scheme :
- `postgres-[profile]`
- `backend-[profile]`
- `frontend-[profile]`

For example, to build and spin up the frontend service with `dev` profile in detached mode :

```commandline
docker compose up -d --build frontend-dev
```

</details>


## Features
 - Separate development and production services
 - Seperate development and production networks
 - Quick and easy spin up
 - PostgreSQL database
 - Spring Boot backend
 - User login with `JWT` access tokens
 - User roles
 - Securised routes using `OAuth2` scopes.
 - Websocket route for server information
 - Administration dashboard
 - Responsive Vue3 frontend
 - Type safety with Typescript.

<details>
    <summary>More detail</summary>

- The database lives in a `Postgresql` container.
- `Java` backend using `SpringBoot`. The backend is served using `tomcat`.
- `Vue3/Typescript` frontend using `vite`. In the development environment the frontend is served using vite, in production `NGINX` is used.
</details>

## To do
- Update README with pictures
- Backend: Implement data validation to DTO classes
- Backend: Fix authorized controller access
- Backend: Implement websocket routes
- Backend: Fix production environment
- DB: Manage migrations
- Frontend: Enhance error/loading handling when accessing backend
