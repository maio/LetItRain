package cz.maio.letitrain.input

import com.badlogic.gdx.Input.Keys.*
import com.badlogic.gdx.InputProcessor
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.World
import cz.maio.letitrain.component.ImageComponent
import cz.maio.letitrain.component.PlayerComponent
import cz.maio.letitrain.component.PlayerInput

class PlayerInputProcessor(
    world: World,
    private val playerCmps: ComponentMapper<PlayerComponent> = world.mapper(),
) : InputProcessor {
    private val playerEntities = world.family(
        allOf = arrayOf(ImageComponent::class, PlayerComponent::class)
    )

    override fun keyDown(keycode: Int): Boolean {
        val player1 = playerEntities.first()

        when (keycode) {
            LEFT -> playerCmps[player1].keysDown += PlayerInput.MoveLeft
            RIGHT -> playerCmps[player1].keysDown += PlayerInput.MoveRight
            UP -> playerCmps[player1].keysDown += PlayerInput.MoveUp
            DOWN -> playerCmps[player1].keysDown += PlayerInput.MoveDown
        }
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        val player1 = playerEntities.first()

        when (keycode) {
            LEFT -> playerCmps[player1].keysDown -= PlayerInput.MoveLeft
            RIGHT -> playerCmps[player1].keysDown -= PlayerInput.MoveRight
            UP -> playerCmps[player1].keysDown -= PlayerInput.MoveUp
            DOWN -> playerCmps[player1].keysDown -= PlayerInput.MoveDown
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