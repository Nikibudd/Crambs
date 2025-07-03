package io.github.nikibudd.crambs.events.handlers

import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import java.util.*
import kotlin.collections.HashMap

class KeyEventHandler: EventHandler<KeyEvent>{

    private var keys: MutableMap<KeyCode, Boolean> = EnumMap(KeyCode::class.java)

    constructor(vararg keyCodes: KeyCode) {
        keyCodes.forEach { key ->
            keys[key] = false
        }
    }

    override fun handle(event: KeyEvent?) {
        event?.let {
            when (event.eventType) {
                KeyEvent.KEY_PRESSED -> {
                    if (keys.keys.contains(event.code)) {
                        keys[event.code] = true
                    }
                }
                KeyEvent.KEY_RELEASED -> {
                    if (keys.keys.contains(event.code)) {
                        keys[event.code] = false
                    }
                }
            }
        }
    }

    fun isPressed(keyCode: KeyCode): Boolean {
        return keys[keyCode] ?: false
    }
}