package cz.maio.letitrain.component

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

class MoveComponent(
    var dx: Float = 0f,
    var dy: Float = 0f,
) : Component<MoveComponent> {
    override fun type() = MoveComponent

    companion object : ComponentType<MoveComponent>()
}