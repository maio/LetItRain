package cz.maio.letitrain.system

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import cz.maio.letitrain.component.ImageComponent
import cz.maio.letitrain.component.MoveComponent

class MovementSystem : IteratingSystem(
    family { all(ImageComponent, MoveComponent) }
) {
    override fun onTickEntity(entity: Entity) {
        val moveCmp = entity[MoveComponent]
        val imageCmp = entity[ImageComponent]

        imageCmp.image.apply {
            x += (moveCmp.dx * deltaTime)
            y += (moveCmp.dy * deltaTime)
        }
    }
}