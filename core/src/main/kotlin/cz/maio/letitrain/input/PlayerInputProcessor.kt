package cz.maio.letitrain.input

import com.badlogic.gdx.Input.Keys.*
import com.badlogic.gdx.InputProcessor
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.World
import cz.maio.letitrain.component.ImageComponent
import cz.maio.letitrain.component.MoveComponent
import cz.maio.letitrain.component.PlayerComponent

class PlayerInputProcessor(
    world: World,
    private val moveCmps: ComponentMapper<MoveComponent> = world.mapper()
) : InputProcessor {
    private val playerEntities = world.family(
        allOf = arrayOf(ImageComponent::class, PlayerComponent::class)
    )

    override fun keyDown(keycode: Int): Boolean {
        val delta = 5f

        when (keycode) {
            LEFT -> moveCmps[playerEntities.first()].dx = -delta
            RIGHT -> moveCmps[playerEntities.first()].dx = delta
            UP -> moveCmps[playerEntities.first()].dy = delta
            DOWN -> moveCmps[playerEntities.first()].dy = -delta
        }
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            LEFT -> moveCmps[playerEntities.first()].dx = 0f
            RIGHT -> moveCmps[playerEntities.first()].dx = 0f
            UP -> moveCmps[playerEntities.first()].dy = 0f
            DOWN -> moveCmps[playerEntities.first()].dy = 0f
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