package cz.maio.letitrain.input

import com.badlogic.gdx.InputProcessor

class PlayerInputProcessor : InputProcessor {
    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            21 -> println("sipka leva")
            22 -> println("sipka prava")
            19 -> println("sipka nahoru")
            20 -> println("sipka dolu")
        }
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        println(keycode)
        return true
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false
    }
}