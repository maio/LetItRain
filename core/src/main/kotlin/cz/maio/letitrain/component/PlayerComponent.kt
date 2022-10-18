package cz.maio.letitrain.component

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

class PlayerComponent(
    val keysDown: MutableSet<PlayerInput> = HashSet()
) : Component<PlayerComponent> {
    override fun type() = PlayerComponent

    companion object : ComponentType<PlayerComponent>()

    fun hasInput(playerInput: PlayerInput) = playerInput in keysDown
}