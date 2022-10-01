package cz.maio.letitrain.system

import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import cz.maio.letitrain.component.ImageComponent
import cz.maio.letitrain.component.MoveComponent

@AllOf([ImageComponent::class, MoveComponent::class])
class MovementSystem(
    private val moveCmps: ComponentMapper<MoveComponent>,
    private val imgCmps: ComponentMapper<ImageComponent>,
) : IteratingSystem() {
    override fun onTickEntity(entity: Entity) {
        val moveCmp = moveCmps[entity]
        val imageCmp = imgCmps[entity]

        imageCmp.image.apply {
            x += (moveCmp.dx * deltaTime)
            y += (moveCmp.dy * deltaTime)
        }
    }
}