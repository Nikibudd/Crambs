package io.github.nikibudd.crambs.scenes.startmenu

import io.github.nikibudd.crambs.events.handlers.KeyEventHandler
import io.github.nikibudd.crambs.navigation.Navigator
import io.github.nikibudd.crambs.scenes.GameScene
import javafx.scene.canvas.GraphicsContext
import javafx.scene.effect.BlendMode
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import kotlin.math.sin

class StartMenuScene(private val navigator: Navigator, private val name: String) : GameScene(navigator, name) {

    companion object {
        val keyEventHandler = KeyEventHandler(
            KeyCode.SPACE
        )
        val backgroundImage = Image("images/startmenu_background.jpg")
        val logoImage = Image("images/logo.png")

        val TEXT_SCALE = 0.05
        val TEXT_PULSE_MOD = 5
        val TEXT_PULSE_SPEED_MOD = 1.2
        val TEXT_WIDTH_MOD = 0.5
        val TEXT_HEIGHT_MOD = 0.65
    }

    var textPulseScale: Double = 0.0
    var textScaleCycle: Double = 0.0

    override fun init() {
        onKeyPressed = keyEventHandler
        onKeyReleased = keyEventHandler
        this.apply {
            fill = Color.BLACK
        }
        super.init()

    }

    override fun update(deltaInSec: Double) {
        super.update(deltaInSec)
        textScaleCycle += deltaInSec * TEXT_PULSE_SPEED_MOD
        textPulseScale = sin(textScaleCycle)
        if (keyEventHandler.isPressed(KeyCode.SPACE)) {
            println("switching to game stage")
            // TODO: Switch to game stage
        }
    }

    override fun render(gc: GraphicsContext) {
        gc.clear()

        // Background
        gc.drawImage(backgroundImage, 0.0, 0.0)

        // Title
        gc.globalBlendMode = BlendMode.DIFFERENCE
        gc.drawImage(
            logoImage,
            (gc.getCanvasWidth() - logoImage.width) / 2 ,
            (gc.getCanvasHeight() - logoImage.height) / 2
        )
        gc.globalBlendMode = BlendMode.SRC_ATOP

        // Text
        gc.textAlign = TextAlignment.CENTER
        gc.font = Font("Arial Black", width * TEXT_SCALE + textPulseScale * TEXT_PULSE_MOD)
        gc.fillText("Press Space to Start!", gc.getCanvasWidth() * TEXT_WIDTH_MOD, gc.getCanvasHeight() * TEXT_HEIGHT_MOD)
    }

}