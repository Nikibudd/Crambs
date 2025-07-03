package io.github.nikibudd.crambs

import javafx.application.Application
import javafx.stage.Stage

class App(): Application() {
    override fun start(primaryStage: Stage?) {
        primaryStage?.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}