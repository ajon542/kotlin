package downundergames.kotlin

import org.koin.dsl.module
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

val NodeModule = module {

    single<INodeVisitor> { PrinterNodeVisitor() }
    single<INodeTraversalStrategy> { BreadthFirstTraversalStrategy(get()) }
}

class NodeApplication : KoinComponent {

    private val traversalStrategy: INodeTraversalStrategy by inject()

    fun traverseNodes() {
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

        traversalStrategy.traverse(root)
    }
}

fun main(vararg args: String) {

    startKoin {
        logger()
        modules(NodeModule)
    }
    NodeApplication().traverseNodes()
}