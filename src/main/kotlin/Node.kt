package downundergames.kotlin

class Node : INode {
    override var id: Int = 0
    override var children: MutableList<INode> = mutableListOf()
}