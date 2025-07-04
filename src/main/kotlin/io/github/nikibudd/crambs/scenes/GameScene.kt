package io.github.nikibudd.crambs.scenes

import io.github.nikibudd.crambs.navigation.Navigator
import javafx.animation.AnimationTimer
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext

abstract class GameScene(navigator: Navigator, name: String): Scene(root) {

    companion object {
        private val root = Group()
        private val children = root.children
    }

    private var lastTimeInNanoSec: Double = 0.0
    private val canvas: Canvas

    init {
        navigator.registerScene(name, this)
        canvas = Canvas(navigator.getStageWidth(), navigator.getStageHeight())
        children.add(canvas)
        lastTimeInNanoSec = System.nanoTime().toDouble()
        init()
    }

    open fun init() {
        val animationTimer = object : AnimationTimer() {
            override fun handle(now: Long) {
                val deltaInNanoSec = now - lastTimeInNanoSec
                val deltaInSec = deltaInNanoSec / 1e9
                lastTimeInNanoSec = now.toDouble()

                update(deltaInSec)
                render(canvas.graphicsContext2D)
            }
        }
        animationTimer.start()
    }

    abstract fun update(deltaInSec: Double)

    abstract fun render(gc: GraphicsContext)
}