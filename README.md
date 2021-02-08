# Graph-Demo API

An application demo API that implements storing data in a graph and exposes some logics via
REST calls

### Tech Spec
- Java 11
https://www.oracle.com/java/technologies/javase-downloads.html
- Spring boot 2.4.2
- JPA / HIBERNATE as ORM
- Spring JPA Repository - for data access
- H2DB - local in-memory database
- Swagger - API documentation tool https://swagger.io/   
- Lombok (boilerplate code reducer) - Require additional plugin installation to your IDE
https://projectlombok.org/download

### How-to-run
Using Maven:
1. from project root folder _C:\<...>\IdeaProjects\graph-demo>_
`mvn install`
2. _from <...>\graph-demo\graph-application>_ 
`mvn spring-boot:run`

### Tests
NB! The whole project was not entirely covered by the unit tests since they were written for demonstration purposes only.
Only core domain logic was covered. <br>
Test class `com.twentyeightstone.graphdemo.graph.GraphTest`


### API user's manual guide
When the application started on local machine - its is default port 8080
Now we can create a few graphs with vertices and edges, and check whether they are connected or not.
- Open API documentation http://localhost:8080/swagger-ui
###### Graph
- To create a graph open graph-controller and POST `/graph` with unique `graphName` (in the body)
in response will be stored ID of the graph.
- All created graphs (their ids and names only) can be obtained via GET `/graph` 
###### Vertices
- Now we can add multiple Vertices to the graph (1 per request). <br>
Open vertex-controller and create a vertex by POST on `/graph/id/{graph id}/vertex`
with provided vertex name - each vertex name should be unique in scope of a graph. <br>
Repeat for each needed vertex in the graph
5. Here in the same controller we can delete a vertex by name or all vertices in the graph <br>
DELETE `/graph/id/{graph id}/vertex` or `/graph/id/{graph id}/vertex/all`
###### Edges
- To add edges to vertices open edge-controller <br>
POST `/graph/id/{graph id}/edge`
 with body params`edgeName, fromVertexName, toVertexName` <br>
For demo purposes and simplicity - edges are adding by vertex names not by their ids.
 in real world scenario it will be ids of vertices. <br>
Edge name can be not unique. <br>
Edges are one-way directed (A->B) and vertex can be connected even to itself (A->A).
For 2-directional connection add an edge from each side (A->B and B->A) 
- For deleting edges use DELETE `/graph/id/{graph id}/edge`
with parameters `firstVertexName, secondVertexName` <br>
All directly connected edges between 2 vertices will be deleted
###### Checking Graph
In the graph-controller:
-  To Check whether the graph is connected, i.e. there is a path from any vertex to any other vertex
GET `/graph/id/{graphId}/isConnected`
result will be a simple boolean true/false
- To get whole graph structure 
GET `/graph/id/{graphId}` or `/graph/name/{graphName}`


### More about project
The web application designed using DDD (Domain-Driven-Design) approach and separated in several modules according to application layers.
So it means we are using isolated from framework pure core domain logic (module) with aggragat and bounded context.
I our example it is GraphAggragate - a stateful OOP model with encapsalated behaviour of its update.
For application's infrastructure layer is responsible:
 - Repository module - CRUD operations with GraphAggregate in database
 - Service module - Management role for coupling domain logic with repository layer
 - Application module - WEB part of API and Spring framework configuration
 

