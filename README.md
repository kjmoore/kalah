# Kalah

An implementation of 6 stone Kalah via a REST service to allow two players to compete across the network.
 
For information about this game, see: https://en.wikipedia.org/wiki/Kalah

## Kalah Rules
Each of the two players has **six pits** in front of him/her. To the right of the six pits, each player has a larger pit, his
Kalah or house.

At the start of the game, six stones are put in each pit.

The player who begins picks up all the stones in any of their own pits, and sows the stones on to the right, one in
each of the following pits, including his own Kalah. No stones are put in the opponent's' Kalah. If the players last
stone lands in his own Kalah, he gets another turn. This can be repeated any number of times before it's the other
player's turn.

When the last stone lands in an own empty pit, the player captures this stone and all stones in the opposite pit (the
other players' pit) and puts them in his own Kalah.

The game is over as soon as one of the sides run out of stones. The player who still has stones in his/her pits keeps
them and puts them in his/hers Kalah. The winner of the game is the player who has the most stones in his Kalah.


## Getting Started
These instructions will startup a local copy of the project for development and testing. For deployment to production, see deployment

### Building the deployment
This is a gradle project, so can be built with, this will also run the tests:
```
./gradlew build
```
### Running a local copy
Spring can automatically start a local server for you to run the code
```
./gradlew bootRun
```

### Getting started with the codebase
The main point of ingress can be found at `com.kieranjohnmoore.kalah.controller.GameController`, this defines the public facing API

The game logic itself is controlled via a series of rules, defined at `com.kieranjohnmoore.kalah.domain.rule.*` and run by `com.kieranjohnmoore.kalah.service.GameService`

There is a set of tests to test all of the internal logic, along with an integration test which will run a complete game and print the output - this can be used if needed to manually verify the logic.

## Deploying to Production
It is expected that this project would be packaged into a container and deployed onto a Kubernetes cluster.
### Prerequisites
* Docker is installed: `https://www.docker.com/get-started`
* Kubernetes `kubectl` is installed and configured to point to the cluster: `https://kubernetes.io/docs/reference/kubectl/overview/`
 
### Building the container
A gradle plugin can be used to build the docker image:
```
./gradlew docker
```
This plugin can also be used to push the docker image to a docker repository given appropriate permissions
```
./gradlew dockerPush
```
The location and name of this image can be defined in `build.gradle` under the `docker` task
### Deploying to k8s
Once the docker image has been built and pushed to a repository, the files at `{root}/k8s` can be used to create a deployment, run them in the following order
```
kubectl apply -f k8s-deployment.yml
kubectl apply -f k8s-service.yml
kubectl apply -f k8s-ingress.yml
```
Notes:
* The image name in k8s-deployment.yml will need to be updated if the image was deployed to a different location in the building container step
* This ingress is using Traefik and may need to be customised if a different ingress is used on the cluster
* The `imagePullSecrets` in `k8s-deployment.yml` will need to reflect the location where the docker repository is configured

## Usage
### Creating a game
A game must be created by sending a POST message to:
`http{s}://{server}:{port}/games`
This will return an object with an `id` field referencing the new game
eg:
```
curl --header "Content-Type: application/json" --request POST https://kalah.kieranjohnmoore.com/games
```
Response:
```
{"id":"bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac","uri":"http://kalah.kieranjohnmoore.com/games/bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac"}
```

### Joining a game
Using the id from the previous request, two players may join a game with a POST message to:
`http{s}://{server}:{port}/games/{id}/join`
This will return an object with a `player` object with their turn number and a unique token used to play moves
eg:
```
curl --header "Content-Type: application/json" --request POST https://kalah.kieranjohnmoore.com/games/bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac/join
```
Response:
```
{"id":"bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac","player":{"token":"de73fc58-8cda-4c5c-87ea-3803fc32389a","playerNumber":1},"uri":"http://kalah.kieranjohnmoore.com/games/bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac"}
```

### Getting the game state
Using the game id, the state of the game can be retrieved with a GET to:
`http{s}://{server}:{port}/games/{id}`
This will return the details including which player's turn it is, if there is a winner (0 for no winner yet, 1/2 for a winner, 3 for a draw) and the status of the board

The status contains status: json object key-value, where key is the pitId and value is the number of stones in the pit. Pits 7 and 14 are the houses for player 1 and 2 respectively

eg:
```
curl --header "Content-Type: application/json" --request GET https://kalah.kieranjohnmoore.com/games/bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac
```
Response:
```
{"id":"bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac","status":{"1":"6","2":"6","3":"6","4":"6","5":"6","6":"6","7":"0","8":"6","9":"6","10":"6","11":"6","12":"6","13":"6","14":"0"},"playerTurn":1,"winner":0,"uri":"http://kalah.kieranjohnmoore.com/games/bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac"}
```

### Making a move
A move can be made when it is a given player's turn, the id, pitId to play and the player's token must be provided:
`http{s}://{server}:{port}/games/{id}/pits/{pitId}` - The token should be provided in the body of the request

The response will contain a new copy of the game or report an error if the move is invalid.

eg:
```
curl --header "Content-Type: application/json" --request PUT https://kalah.kieranjohnmoore.com/games/bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac/pits/1 -d de73fc58-8cda-4c5c-87ea-3803fc32389a
```
Response:
```
{"id":"bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac","status":{"1":"0","2":"7","3":"7","4":"7","5":"7","6":"7","7":"1","8":"6","9":"6","10":"6","11":"6","12":"6","13":"6","14":"0"},"playerTurn":1,"winner":0,"uri":"http://kalah.kieranjohnmoore.com/games/bbb1cfa6-4b36-4025-a2aa-183f0c0c53ac"}
```
