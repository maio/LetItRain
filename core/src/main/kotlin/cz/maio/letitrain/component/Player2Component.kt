package cz.maio.letitrain.component

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

class Player2Component : Component<Player2Component> {
    override fun type() = Player2Component

    companion object : ComponentType<Player2Component>()
}