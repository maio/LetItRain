package cz.maio.letitrain.system

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import cz.maio.letitrain.component.DropletComponent
import cz.maio.letitrain.component.ImageComponent
import cz.maio.letitrain.component.PlayerComponent

class DropletCollisionSystem : IteratingSystem(
    family { all(DropletComponent, ImageComponent) }
) {
    private val players = world.family { all(PlayerComponent, ImageComponent) }

    override fun onTickEntity(entity: Entity) {
        val dropletImageCmp = entity[ImageComponent]
        val dx = dropletImageCmp.image.x
        val dy = dropletImageCmp.image.y
        val dw = dropletImageCmp.image.width
        val dh = dropletImageCmp.image.height

        val dcx = dx + dw / 2
        val dcy = dy + dh / 2

        players.forEach { playerEntity ->
            val playerImageCmp = playerEntity[ImageComponent]
            val px = playerImageCmp.image.x
            val py = playerImageCmp.image.y
            val pw = playerImageCmp.image.width
            val ph = playerImageCmp.image.height
            val px2 = px + pw
            val py2 = py + ph

            if (dcx in px..px2) {
                if (dcy in py..py2) {
                    println("HIT")
                }
            }
        }
    }
}