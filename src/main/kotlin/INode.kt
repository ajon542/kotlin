package downundergames.kotlin

interface INode {
    var id: Int
    var children: MutableList<INode>
}