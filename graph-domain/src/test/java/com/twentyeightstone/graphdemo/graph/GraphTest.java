package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.exception.InconsistentStateException;
import com.twentyeightstone.graphdemo.exception.UniqueNameConstraintException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class GraphTest {

    private static final String V_PARIS = "Paris";
    private static final String V_MILAN = "Milan";
    private static final String V_ZAGREB = "Zagreb";
    private static final String V_PRAGUE = "Prague";
    private static final String E_SOUTH = "South";
    private static final String E_EAST = "East";
    private static final String E_WEST = "West";
    private static final String E_NORTH = "North";

    @Test
    public void testAddVertexSuccessfully() {
        var graph = createGraph();
        graph.addVertex(V_PARIS);
        graph.addVertex(V_ZAGREB);
        assertEquals(2, graph.getVertices().size());
        assertEquals(V_PARIS, graph.getVertices().get(0).getName());
        assertEquals(V_ZAGREB, graph.getVertices().get(1).getName());
    }

    @Test
    public void testAddVertexNameConstraint() {
        try {
            var graph = createGraph();
            graph.addVertex(V_PARIS);
            graph.addVertex(V_PARIS);
            fail();
        } catch (UniqueNameConstraintException ex) {
            assertEquals("Vertex with name Paris already exists in the graph", ex.getMessage());
        }
    }

    @Test
    public void testRemoveVertex() {
        var graph = createNotFullyConnectedGraph();
        assertTrue(graph.getRemovedVertices().isEmpty());
        graph.removeVertex(V_MILAN);
        assertFalse(graph.getVertices().stream()
                .anyMatch(vertex -> vertex.isEqualsByName(V_MILAN))
        );
        assertFalse(graph.getVertices()
                .stream()
                .flatMap(vertex -> vertex.getEdges().stream())
                .anyMatch(edge -> edge.getHeadToVertex().isEqualsByName(V_MILAN))
        );
        assertEquals(1, graph.getRemovedVertices().size());
        assertEquals(V_MILAN, graph.getRemovedVertices().get(0).getName());
    }

    @Test
    public void testRemoveAllDirectEdgesBetween() {
        var graph = createNotFullyConnectedGraph();
        var zagreb = graph.findVertexByName(V_ZAGREB);
        var milan = graph.findVertexByName(V_MILAN);
        assertTrue(zagreb.getRemovedEdges().isEmpty());
        assertTrue(milan.getRemovedEdges().isEmpty());
        assertTrue(zagreb.getEdges().stream()
                .anyMatch(edge -> edge.getHeadToVertex().isEqualsByName(V_MILAN))
        );
        assertTrue(milan.getEdges().stream()
                .anyMatch(edge -> edge.getHeadToVertex().isEqualsByName(V_ZAGREB))
        );
        graph.removeAllDirectEdgesBetween(V_ZAGREB, V_MILAN);

        assertEquals(1, zagreb.getRemovedEdges().size());
        assertEquals(1, milan.getRemovedEdges().size());
        assertFalse(zagreb.getEdges().stream()
                .anyMatch(edge -> edge.getHeadToVertex().isEqualsByName(V_MILAN))
        );
        assertFalse(milan.getEdges().stream()
                .anyMatch(edge -> edge.getHeadToVertex().isEqualsByName(V_ZAGREB))
        );
        assertTrue(milan.getEdges().stream()
                .anyMatch(edge -> edge.getHeadToVertex().isEqualsByName(V_MILAN))
        );
    }

    @Test
    public void testRemoveAllEdges() {
        var graph = createNotFullyConnectedGraph();
        graph.removeAllEdges();
        assertEquals(0, graph.getVertices().stream()
                .mapToLong(vertex -> vertex.getEdges().size())
                .sum()
        );
    }

    @Test
    public void testIsConnectedOnEmptyGraph() {
        try{
            createGraph().isConnected();
            fail();
        } catch (InconsistentStateException ex) {
            assertEquals("Could not check the graph since there is no vertices", ex.getMessage());
        }
    }

    @Test
    public void testNotConnectedGraph() {
        assertFalse(createNotFullyConnectedGraph().isConnected());

        var graphFor2Vertices = createGraph();
        graphFor2Vertices.addVertex(V_ZAGREB);
        graphFor2Vertices.addVertex(V_PARIS);
        assertFalse(graphFor2Vertices.isConnected());
    }

    @Test
    public void testConnectedGraph() {
        var singleVertexGraph = createGraph();
        singleVertexGraph.addVertex(V_ZAGREB);
        assertTrue(singleVertexGraph.isConnected());

        var forSelfConnectedVertex = createGraph();
        forSelfConnectedVertex.addVertex(V_ZAGREB);
        forSelfConnectedVertex.addEdge(E_NORTH, null, V_ZAGREB, V_ZAGREB);
        assertTrue(forSelfConnectedVertex.isConnected());

        assertTrue(createCycledConnectedGraph().isConnected());
        assertTrue(createFullyConnectedGraph().isConnected());
    }


    private Graph createCycledConnectedGraph() {
        Graph graph = createGraphWithVertices();
        graph.addEdge(E_SOUTH, null, V_PARIS, V_MILAN);
        graph.addEdge(E_EAST, 1L, V_MILAN, V_ZAGREB);
        graph.addEdge(E_NORTH, 2L, V_ZAGREB, V_PRAGUE);
        graph.addEdge(E_WEST, 3L, V_PRAGUE, V_PARIS);
        assertEquals(4, graph.getVertices().stream()
                .mapToLong(vertex -> vertex.getEdges().size())
                .sum()
        );
        return graph;
    }

    private Graph createFullyConnectedGraph() {
        Graph graph = createGraphWithVertices();
        graph.addEdge(E_SOUTH, 1L, V_PARIS, V_PARIS);
        graph.addEdge(E_SOUTH, 2L, V_PARIS, V_MILAN);
        graph.addEdge(E_EAST, 3L, V_PARIS, V_ZAGREB);
        graph.addEdge(E_EAST, 4L, V_PARIS, V_PRAGUE);

        graph.addEdge(E_EAST, 5L, V_MILAN, V_MILAN);
        graph.addEdge(E_EAST, 6L, V_MILAN, V_ZAGREB);
        graph.addEdge(E_NORTH, 7L, V_MILAN, V_PARIS);
        graph.addEdge(E_EAST, 8L, V_MILAN, V_PRAGUE);

        graph.addEdge(E_WEST, 9L, V_ZAGREB, V_ZAGREB);
        graph.addEdge(E_WEST, 10L, V_ZAGREB, V_MILAN);
        graph.addEdge(E_WEST, 11L, V_ZAGREB, V_PARIS);
        graph.addEdge(E_NORTH, 12L, V_ZAGREB, V_PRAGUE);

        graph.addEdge(E_NORTH, 13L, V_PRAGUE, V_PRAGUE);
        graph.addEdge(E_WEST, 14L, V_PRAGUE, V_PARIS);
        graph.addEdge(E_SOUTH, 15L, V_PRAGUE, V_ZAGREB);
        graph.addEdge(E_SOUTH, 16L, V_PRAGUE, V_MILAN);
        assertEquals(16, graph.getVertices().stream()
                .mapToLong(vertex -> vertex.getEdges().size())
                .sum()
        );
        return graph;
    }

    private Graph createNotFullyConnectedGraph() {
        Graph graph = createGraphWithVertices();
        graph.addEdge(E_SOUTH, null, V_PARIS, V_MILAN);
        graph.addEdge(E_EAST, 1L, V_MILAN, V_MILAN);
        graph.addEdge(E_EAST, 1L, V_MILAN, V_ZAGREB);
        graph.addEdge(E_WEST, 2L, V_ZAGREB, V_MILAN);
        graph.addEdge(E_NORTH, 3L, V_ZAGREB, V_PARIS);
        assertEquals(5, graph.getVertices().stream()
                .mapToLong(vertex -> vertex.getEdges().size())
                .sum()
        );
        return graph;
    }

    private Graph createGraphWithVertices() {
        Graph graph = createGraph();
        graph.addVertex(V_PARIS);
        graph.addVertex(V_MILAN);
        graph.addVertex(V_ZAGREB);
        graph.addVertex(V_PRAGUE);
        assertEquals(4, graph.getVertices().size());
        return graph;
    }

    private Graph createGraph() {
        return new Graph(1L, "G1");
    }
}