package cz.maio.letitrain.input

import com.badlogic.gdx.InputProcessor
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.World
import cz.maio.letitrain.component.ImageComponent
import cz.maio.letitrain.component.MoveComponent

class PlayerInputProcessor(
    world: World,
    private val moveCmps: ComponentMapper<MoveComponent> = world.mapper()
) : InputProcessor {
    private val playerEntities = world.family(allOf = arrayOf(ImageComponent::class))

    override fun keyDown(keycode: Int): Boolean {
        val delta = 5f

        when (keycode) {
            21 -> {
                moveCmps[playerEntities.first()].dx = -delta
                println("sipka leva")
            }

            22 -> {
                moveCmps[playerEntities.first()].dx = delta
                println("sipka prava")
            }

            19 -> {
                moveCmps[playerEntities.first()].dy = delta
                println("sipka nahoru")
            }

            20 -> {
                moveCmps[playerEntities.first()].dy = -delta
                println("sipka dolu")
            }
        }
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            21 -> {
                moveCmps[playerEntities.first()].dx = 0f
                println("sipka leva")
            }

            22 -> {
                moveCmps[playerEntities.first()].dx = 0f
                println("sipka prava")
            }

            19 -> {
                moveCmps[playerEntities.first()].dy = 0f
                println("sipka nahoru")
            }

            20 -> {
                moveCmps[playerEntities.first()].dy = 0f
                println("sipka dolu")
            }
        }
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