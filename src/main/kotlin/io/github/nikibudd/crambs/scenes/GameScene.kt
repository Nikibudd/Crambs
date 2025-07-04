package io.github.nikibudd.crambs.scenes

import io.github.nikibudd.crambs.navigation.Navigator
import javafx.animation.AnimationTimer
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.transform.Scale

abstract class GameScene(navigator: Navigator, name: String): Scene(StackPane()) {

    companion object {
        private const val TARGET_ASPECT_RATIO = 16.0 / 9.0
        private const val TARGET_WIDTH = 1920.0
        private const val TARGET_HEIGHT = 1080.0
    }

    private var lastTimeInNanoSec: Double
    private val canvas: Canvas
    private val navigator: Navigator
    private val canvasContainer: Pane

    init {
        this.navigator = navigator
        this.navigator.registerScene(name, this)

        canvas = Canvas(TARGET_WIDTH, TARGET_HEIGHT)

        canvasContainer = object : Pane() {
            override fun layoutChildren() {
                val containerWidth = width
                val containerHeight = height

                if (containerWidth > 0 && containerHeight > 0) {
                    val scaleX = containerWidth / TARGET_WIDTH
                    val scaleY = containerHeight / TARGET_HEIGHT
                    val scale = minOf(scaleX, scaleY)

                    canvas.transforms.clear()
                    canvas.transforms.add(Scale(scale, scale))

                    val scaledWidth = TARGET_WIDTH * scale
                    val scaledHeight = TARGET_HEIGHT * scale
                    val x = (containerWidth - scaledWidth) / 2
                    val y = (containerHeight - scaledHeight) / 2

                    canvas.layoutX = x
                    canvas.layoutY = y
                }
            }

            override fun computePrefWidth(height: Double): Double {
                return if (height != -1.0) height * TARGET_ASPECT_RATIO else TARGET_WIDTH
            }

            override fun computePrefHeight(width: Double): Double {
                return if (width != -1.0) width / TARGET_ASPECT_RATIO else TARGET_HEIGHT
            }
        }

        canvasContainer.children.add(canvas)
        (root as StackPane).children.add(canvasContainer)

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

    open fun update(deltaInSec: Double) {

    }

    abstract fun render(gc: GraphicsContext)

    fun GraphicsContext.clear() {
        clearRect(0.0, 0.0, canvas.width, canvas.height)
    }
}