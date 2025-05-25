package com.crowdin.client.core;

import com.crowdin.client.core.model.GraphQLRequest;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import lombok.Data;
import lombok.var;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrowdinApiTest extends TestClient {

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.graphqlUrl, HttpPost.METHOD_NAME, "api/core/graphQLRequest.json",
                        "api/core/graphQLResponse.json")
        );
    }

    @Test
    public void graphQLTest() {
        var res = this.graphql(new GraphQLRequest("query { viewer { projects(first: 2) { edges { node { name } } } } }"));
        var edges = List.class.cast(Map.class.cast(Map.class.cast(Map.class.cast(res.getData().get("viewer")).get("projects"))).get("edges"));
        assertEquals(2, edges.size());
        assertEquals("test1", Map.class.cast(Map.class.cast(edges.get(0)).get("node")).get("name").toString());
        assertEquals("test2", Map.class.cast(Map.class.cast(edges.get(1)).get("node")).get("name").toString());
    }

    @Test
    public void graphQLCustomTypeTest() {
        var res = this.graphql(new GraphQLRequest("query { viewer { projects(first: 2) { edges { node { name } } } } }"), GraphQLRes.class);
        assertEquals(2, res.getData().getViewer().getProjects().getEdges().size());
        assertEquals("test1", res.getData().getViewer().getProjects().getEdges().get(0).getNode().getName());
        assertEquals("test2", res.getData().getViewer().getProjects().getEdges().get(1).getNode().getName());
    }

    @Data
    public static class GraphQLRes {

        private GraphQLResData data;

        @Data
        public static class GraphQLResData {
            private GraphQLResViewer viewer;
        }

        @Data
        public static class GraphQLResViewer {
            private GraphQLResProjects projects;
        }

        @Data
        public static class GraphQLResProjects {
            private List<GraphQLResEdge> edges;
        }

        @Data
        public static class GraphQLResEdge {
            private GraphQLResNode node;
        }

        @Data
        public static class GraphQLResNode {
            private String name;
        }
    }
}
