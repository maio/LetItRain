package cz.maio.letitrain.input

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputProcessor
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
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
        val (player1, player2) = getPlayerEntitiesAsList()

        val player2Cmp = playerCmps[player2]
        val player1Cmp = playerCmps[player1]

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
        val (player1, player2) = getPlayerEntitiesAsList()

        val player2Cmp = playerCmps[player2]
        val player1Cmp = playerCmps[player1]

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

    private fun getPlayerEntitiesAsList(): List<Entity> {
        val players = mutableListOf<Entity>()
        playerEntities.forEach { players.add(it) }
        return players.reversed()
    }
}