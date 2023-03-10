package com.example.pregnantpal.Pickle

import java.io.Serializable

class RandomForestClassifier(val n_estimators: Int, val tree_depth: Int, val min_samples_split: Int) : Serializable {

    private val trees: MutableList<DecisionTreeClassifier> = mutableListOf()

    fun predict(X: Array<FloatArray>): IntArray {
        val n_samples = X.size
        val y_pred = IntArray(n_samples)

        for (i in 0 until n_samples) {
            val predictionVotes = IntArray(n_classes())

            for (tree in trees) {
                val prediction = tree.predict(X[i])
                predictionVotes[prediction] += 1
            }

            y_pred[i] = predictionVotes.indexOf(predictionVotes.maxOrNull() ?: 0)
        }

        return y_pred
    }

    fun addTree(tree: DecisionTreeClassifier) {
        trees.add(tree)
    }

    fun n_classes(): Int {
        return trees[0].n_classes()
    }
}

