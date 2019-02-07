package downundergames.kotlin

import java.util.*

class DepthFirstTraversalStrategy (visitor: INodeVisitor) : INodeTraversalStrategy {

    val _visitor : INodeVisitor = visitor

    override fun traverse(node: INode) {

        val nodeStack : Stack<INode> = Stack()
        nodeStack.push(node)

        while (!nodeStack.isEmpty()) {
            var currentNode = nodeStack.pop()
            _visitor.visit(currentNode)
            for (childNode in currentNode.children)
                nodeStack.push(childNode)
        }
    }
}