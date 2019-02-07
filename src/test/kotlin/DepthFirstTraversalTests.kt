package downundergames.kotlin.tests

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.Runs
import io.mockk.verify
import org.junit.Test
import org.junit.Assert.assertEquals
import downundergames.kotlin.INode
import downundergames.kotlin.INodeVisitor
import downundergames.kotlin.DepthFirstTraversalStrategy
import downundergames.kotlin.Node

class DepthFirstTraversalTests {

    @Test
    fun testSingleNodeDepthFirst() {
        var node = Node().apply { id = 1 }

        val list = mutableListOf<INode>()
        val visitor = mockk<INodeVisitor>(relaxed = true)
        every { visitor.visit(capture(list)) } just Runs

        var traversalStrategy = DepthFirstTraversalStrategy(visitor)

        traversalStrategy.traverse(node)

        verify { visitor.visit(node) }

        assertEquals(1, list[0].id)
    }

    @Test
    fun testSingleChildNodeDepthFirst() {
        var node = Node().apply {
            id = 0
            children = mutableListOf(Node().apply { id = 1 })
        }

        val list = mutableListOf<INode>()
        val visitor = mockk<INodeVisitor>(relaxed = true)
        every { visitor.visit(capture(list)) } just Runs

        var traversalStrategy = DepthFirstTraversalStrategy(visitor)

        traversalStrategy.traverse(node)

        verify { visitor.visit(node) }

        assertEquals(0, list[0].id)
        assertEquals(1, list[1].id)
    }

    @Test
    fun testMultipleChildNodesDepthFirst() {
        var node = Node().apply {
            id = 0
            children = mutableListOf(Node().apply { id = 1 }, Node().apply { id = 2 }, Node().apply { id = 3 })
        }

        val list = mutableListOf<INode>()
        val visitor = mockk<INodeVisitor>(relaxed = true)
        every { visitor.visit(capture(list)) } just Runs

        var traversalStrategy = DepthFirstTraversalStrategy(visitor)

        traversalStrategy.traverse(node)

        verify { visitor.visit(node) }

        assertEquals(0, list[0].id)
        assertEquals(3, list[1].id)
        assertEquals(2, list[2].id)
        assertEquals(1, list[3].id)
    }

    @Test
    fun testFullNodeTreeDepthFirst() {

        var aaa = Node().apply { id = 4 }
        var aab = Node().apply { id = 5 }
        var aac = Node().apply { id = 6 }

        var aba = Node().apply { id = 7 }
        var abb = Node().apply { id = 8 }
        var abc = Node().apply { id = 9 }

        var aca = Node().apply { id = 10 }
        var acb = Node().apply { id = 11 }
        var acc = Node().apply { id = 12 }

        var aa = Node().apply {
            id = 1
            children = mutableListOf(aaa, aab, aac)
        }

        var ab = Node().apply {
            id = 2
            children = mutableListOf(aba, abb, abc)
        }

        var ac = Node().apply {
            id = 3
            children = mutableListOf(aca, acb, acc)
        }

        var root = Node().apply {
            id = 0
            children = mutableListOf(aa, ab, ac)
        }

        val list = mutableListOf<INode>()
        val visitor = mockk<INodeVisitor>(relaxed = true)
        every { visitor.visit(capture(list)) } just Runs

        var traversalStrategy = DepthFirstTraversalStrategy(visitor)

        traversalStrategy.traverse(root)

        verify { visitor.visit(root) }

        assertEquals(0, list[0].id)
        assertEquals(3, list[1].id)
        assertEquals(12, list[2].id)
        assertEquals(11, list[3].id)
        assertEquals(10, list[4].id)
        assertEquals(2, list[5].id)
        assertEquals(9, list[6].id)
        assertEquals(8, list[7].id)
        assertEquals(7, list[8].id)
        assertEquals(1, list[9].id)
        assertEquals(6, list[10].id)
        assertEquals(5, list[11].id)
        assertEquals(4, list[12].id)
    }
}
