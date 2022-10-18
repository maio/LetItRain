package cz.maio.letitrain.component

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

class Player1Component : Component<Player1Component> {
    override fun type() = Player1Component

    companion object : ComponentType<Player1Component>()
}