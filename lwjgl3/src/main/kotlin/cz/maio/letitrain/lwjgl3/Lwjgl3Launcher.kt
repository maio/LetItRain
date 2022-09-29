@file:JvmName("Lwjgl3Launcher")

package cz.maio.letitrain.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import cz.maio.letitrain.LetItRain

/** Launches the desktop (LWJGL3) application. */
fun main() {
    Lwjgl3Application(LetItRain(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("letitrain")
        setWindowedMode(640, 480)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
