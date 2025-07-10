package io.github.nikibudd.crambs

import io.github.nikibudd.crambs.navigation.Navigator
import io.github.nikibudd.crambs.scenes.startmenu.StartMenuScene
import javafx.application.Application
import javafx.stage.Stage

class App(): Application() {
    override fun start(primaryStage: Stage?) {
        primaryStage?.let {
            primaryStage.apply {
                isFullScreen = true
                x = 0.0
                y = 0.0
                isMaximized = true
            }
            val navigator = Navigator(primaryStage)
            val startMenuScene = StartMenuScene(navigator, "startmenu")
            navigator.navigateTo("startmenu")
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}