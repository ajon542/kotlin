package downundergames.kotlin

import java.util.*

class BreadthFirstTraversalStrategy (visitor: INodeVisitor) : INodeTraversalStrategy {

    val _visitor : INodeVisitor = visitor

    override fun traverse(node: INode) {

        val nodeQueue : Queue<INode> = LinkedList()
        nodeQueue.add(node)

        while (!nodeQueue.isEmpty()) {
            var currentNode = nodeQueue.remove()
            _visitor.visit(currentNode)
            for (childNode in currentNode.children)
                nodeQueue.add(childNode)
        }
    }
}