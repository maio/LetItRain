package cz.maio.letitrain.system

import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import cz.maio.letitrain.component.MoveComponent
import cz.maio.letitrain.component.PlayerComponent
import cz.maio.letitrain.component.PlayerInput

@AllOf([PlayerComponent::class, MoveComponent::class])
class PlayerMovementSystem(
    private val moveCmps: ComponentMapper<MoveComponent>,
    private val playerCmps: ComponentMapper<PlayerComponent>,
) : IteratingSystem() {
    override fun onTickEntity(entity: Entity) {
        val moveCmp = moveCmps[entity]
        val playerCmp = playerCmps[entity]
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