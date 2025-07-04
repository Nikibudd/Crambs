package io.github.nikibudd.crambs.navigation

import javafx.scene.Scene
import javafx.stage.Stage

class Navigator {

    private val stage: Stage
    private var scenes: MutableMap<String, Scene>

    constructor(stage: Stage) {
        this.stage = stage
        this.scenes = HashMap()
    }

    fun registerScene(name: String, scene: Scene) {
        scenes[name] = scene
    }

    fun navigateTo(name: String) {
        stage.scene = scenes[name]
        stage.show()
    }

    fun getStageWidth(): Double {
        return stage.width
    }

    fun getStageHeight(): Double {
        return stage.height
    }

}