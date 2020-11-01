package gr.rongasal.chess.game.algorithm;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is used to construct a graph double linked tree and is used from algorithms to denote possible paths
 * @param <T> graph node
 */
@Getter
@Setter
@ToString(exclude = "parent")
@EqualsAndHashCode
class PathElement<T extends Node> {
    private T position;
    private List<PathElement<T>> children;
    private PathElement<T> parent;

    PathElement(T position) {
        this(position, new ArrayList<>());
    }

    private PathElement(T position, List<PathElement<T>> children) {
        this.position = position;
        this.children = children;
    }

    void addPathElement(PathElement<T> pathElement) {
        children.add(pathElement);
        pathElement.parent = this;
    }

    boolean isPartOfPath(PathElement pathElement) {
        return hasParent(pathElement);
    }

    private boolean hasParent(PathElement pathElement) {
        if (this.parent == null) {
            return this.getPosition().equals(pathElement.getPosition());
        } else {
            if (this.getParent().getPosition().equals(pathElement.getPosition())) {
                return true;
            } else {
                return this.parent.hasParent(pathElement);
            }
        }
    }

    /**
     * This method returns the list of possible paths from this path element tree.
     * @return empty or list of possible routes found from algorithm
     */
    Optional<List<List<T>>> toRoutes() {
        if (this.getChildren().isEmpty()) { // no paths. only root item
            return Optional.empty();
        } else {
            List<List<T>> routes = new ArrayList<>();
            List<PathElement<T>> treeLeaves = new ArrayList<>();
            getTreeLeaves(treeLeaves);   //get all bottom nodes
            for (PathElement<T> treeLeaf : treeLeaves) {
                List<T> route = new ArrayList<>();
                expand(treeLeaf, route); //add to route list all graph nodes from parent to leaf utilizing parent property of path element. Leaf is provided
                routes.add(route);
            }

            return Optional.of(routes);
        }
    }

    /**
     * starting from leaf add all possitions untill top parent of tree
     */
    private void expand(PathElement<T> treeLeaf, List<T> route) {
        route.add(0, treeLeaf.getPosition());
        if (treeLeaf.getParent() != null) {
            expand(treeLeaf.getParent(), route);
        }
    }

    /**
     *  adds to treeLeaves all tree noders that have no children
     */
    private void getTreeLeaves(List<PathElement<T>> treeLeaves) {
        if (this.children.size() > 0) {
            this.getChildren().forEach(l -> l.getTreeLeaves(treeLeaves));
        } else {
            treeLeaves.add(this);
        }
    }
}
