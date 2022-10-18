package cz.maio.letitrain.component

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

class DropletComponent : Component<DropletComponent> {
    override fun type() = DropletComponent

    companion object : ComponentType<DropletComponent>()
}