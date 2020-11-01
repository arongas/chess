package gr.rongasal.chess.game.algorithm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PossiblePathsAlgorithm implements Algorithm {
    private ConnectionProvider<Node> connectionProvider;
    private int exploreDepth;

    /**
     * Walks through the possible moves starting from start node until either reaching target node or
     * reaching the maximum explore depth. The graph is provided through connection provider.
     * @param startNode the start node
     * @param targetNode the end node
     * @return empty if no solution found or a list of paths. Each path is denoted as a list of graph nodes
     * @see ConnectionProvider
     */
    @Override
    public Optional<List<List<Node>>> walk(Node startNode, Node targetNode) {
        PathElement<Node> pathElement = new PathElement<>(startNode);
        log.debug("Walk start {} until {} with depth {}", startNode,targetNode,exploreDepth);
        walk(pathElement, targetNode, exploreDepth);
        Optional<List<List<Node>>> possiblePaths = pathElement.toRoutes();
        log.debug("Walk start {} until {} with depth {} result is {}", startNode,targetNode,exploreDepth, possiblePaths);
        return possiblePaths;
    }

    private boolean walk(PathElement<Node> path, Node targetPosition, int depth) {
        log.debug("Visited {} with depth {}", path, depth);
        boolean found = false;
        if (depth != 0 && path.getPosition().isValid()) {  //in case path is not valid or consumed max explore depth just return
            Node  current = path.getPosition();
            List<PathElement<Node>> pathElements = connectionProvider.next(current).stream()
                    .filter(Node::isValid).map(PathElement::new).filter(pe->!path.isPartOfPath(pe))
                    .collect(Collectors.toList()); // from connection provider get connected graph nodes with current hop. Exclude nodes that are invalid or part of path to avoid cicles
            // iterate to path elements. If target position has been reached add to path and return true to indicate that this path has reached it destination
            // if max depth is not reached and did not reach final target make a hop and call walk, if it returns true then we can reach destination so add current hop to path
            for (PathElement<Node> pathElement : pathElements) {
                if (!targetPosition.equals(pathElement.getPosition())) {
                    pathElement.setParent(path); //used in recursion so that path.isPartOfPath(pe) works fine
                    if (walk(pathElement, targetPosition, depth - 1)) {
                        path.addPathElement(pathElement);
                        found = true;
                    }
                } else {
                    path.addPathElement(pathElement);
                    found = true;
                }
            }
        }
        return found;
    }

}
