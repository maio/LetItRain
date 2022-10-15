package cz.maio.letitrain.system

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Scaling
import com.github.quillraven.fleks.Fixed
import com.github.quillraven.fleks.IntervalSystem
import cz.maio.letitrain.component.DropletComponent
import cz.maio.letitrain.component.ImageComponent
import cz.maio.letitrain.component.MoveComponent
import ktx.assets.toInternalFile
import kotlin.random.Random

class DropletSpawningSystem : IntervalSystem(interval = Fixed(1f)) {
    private val dropletTexture = Texture("droplet.png".toInternalFile(), true).apply {
        setFilter(
            Texture.TextureFilter.Linear,
            Texture.TextureFilter.Linear
        )
    }

    init {
        onTick()
    }

    override fun onTick() {
        spawnDroplet()
    }

    private fun spawnDroplet() {
        val width = 16f // TODO: stage.width
        val height = 9f // TODO: stage.height

        world.entity {
            add<DropletComponent>()
            add<ImageComponent> {
                image = Image(dropletTexture).apply {
                    setScaling(Scaling.fit)
                    setSize(0.5f, 0.5f)
                    setPosition((width / 2) - 0.5f, 6f)
                }
                image.y = height
                image.x = Random.nextFloat() * width
            }
            val defaultSpeed = -2f

            add<MoveComponent> {
                dy = (Random.nextFloat() * defaultSpeed * 0.2f) + defaultSpeed
            }
        }
    }
}