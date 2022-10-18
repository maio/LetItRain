package cz.maio.letitrain.input

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputProcessor
import com.github.quillraven.fleks.World
import cz.maio.letitrain.component.*

class PlayerInputProcessor(
    private val world: World,
) : InputProcessor {
    private val player1Entities = world.family { all(ImageComponent, PlayerComponent, Player1Component) }
    private val player2Entities = world.family { all(ImageComponent, PlayerComponent, Player2Component) }

    private val player1Entity get() = player1Entities.first()
    private val player2Entity get() = player2Entities.first()

    override fun keyDown(keycode: Int): Boolean {
        val player1Cmp = with(world) { player1Entity[PlayerComponent] }
        val player2Cmp = with(world) { player2Entity[PlayerComponent] }

        when (keycode) {
            Keys.LEFT -> player1Cmp.keysDown += PlayerInput.MoveLeft
            Keys.RIGHT -> player1Cmp.keysDown += PlayerInput.MoveRight
            Keys.UP -> player1Cmp.keysDown += PlayerInput.MoveUp
            Keys.DOWN -> player1Cmp.keysDown += PlayerInput.MoveDown

            Keys.A -> player2Cmp.keysDown += PlayerInput.MoveLeft
            Keys.D -> player2Cmp.keysDown += PlayerInput.MoveRight
            Keys.W -> player2Cmp.keysDown += PlayerInput.MoveUp
            Keys.S -> player2Cmp.keysDown += PlayerInput.MoveDown
        }
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        val player1Cmp = with(world) { player1Entity[PlayerComponent] }
        val player2Cmp = with(world) { player2Entity[PlayerComponent] }

        when (keycode) {
            Keys.LEFT -> player1Cmp.keysDown -= PlayerInput.MoveLeft
            Keys.RIGHT -> player1Cmp.keysDown -= PlayerInput.MoveRight
            Keys.UP -> player1Cmp.keysDown -= PlayerInput.MoveUp
            Keys.DOWN -> player1Cmp.keysDown -= PlayerInput.MoveDown

            Keys.A -> player2Cmp.keysDown -= PlayerInput.MoveLeft
            Keys.D -> player2Cmp.keysDown -= PlayerInput.MoveRight
            Keys.W -> player2Cmp.keysDown -= PlayerInput.MoveUp
            Keys.S -> player2Cmp.keysDown -= PlayerInput.MoveDown
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