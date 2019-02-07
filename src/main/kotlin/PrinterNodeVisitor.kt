package downundergames.kotlin

class PrinterNodeVisitor : INodeVisitor {
    override fun visit(node: INode) {
        println("Node.Id ${node.id}")
    }
}