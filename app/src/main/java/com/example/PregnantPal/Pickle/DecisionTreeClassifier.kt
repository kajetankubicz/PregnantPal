package com.example.pregnantpal.Pickle

import java.io.Serializable

class DecisionTreeClassifier(val max_depth: Int, val min_samples_split: Int) : Serializable {

    private lateinit var tree: TreeNode

    fun predict(X: FloatArray): Int {
        var node = tree
        while (!node.isLeaf) {
            if (X[node.featureIndex] < node.threshold) {
                node = node.left!!
            } else {
                node = node.right!!
            }
        }
        return node.label
    }

    fun setTree(newTree: TreeNode) {
        tree = newTree
    }

    fun n_classes(): Int {
        return tree.n_classes()
    }
}

class TreeNode(val featureIndex: Int, val threshold: Float, val isLeaf: Boolean, val label: Int) : Serializable {
    var left: TreeNode? = null
    var right: TreeNode? = null

    fun n_classes(): Int {
        return 1
    }
}
