package cz.maio.letitrain.system

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import cz.maio.letitrain.component.MoveComponent
import cz.maio.letitrain.component.PlayerComponent
import cz.maio.letitrain.component.PlayerInput

class PlayerMovementSystem : IteratingSystem(
    family { all(PlayerComponent, MoveComponent) }
) {
    override fun onTickEntity(entity: Entity) {
        val moveCmp = entity[MoveComponent]
        val playerCmp = entity[PlayerComponent]
        val delta = 5f

        moveCmp.dx = 0f
        moveCmp.dy = 0f

        if (playerCmp.hasInput(PlayerInput.MoveLeft)) {
            moveCmp.dx += -delta
        }

        if (playerCmp.hasInput(PlayerInput.MoveRight)) {
            moveCmp.dx += delta
        }

        if (playerCmp.hasInput(PlayerInput.MoveUp)) {
            moveCmp.dy += delta
        }

        if (playerCmp.hasInput(PlayerInput.MoveDown)) {
            moveCmp.dy += -delta
        }
    }
}