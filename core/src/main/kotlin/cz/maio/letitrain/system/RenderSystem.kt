package cz.maio.letitrain.system

import com.badlogic.gdx.scenes.scene2d.Stage
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.Qualifier
import cz.maio.letitrain.component.ImageComponent

@AllOf(components = [ImageComponent::class])
class RenderSystem(
    @Qualifier("GameStage") private val stage: Stage
) : IteratingSystem() {
    override fun onTick() {
        super.onTick()
        stage.viewport.apply()
        stage.act(deltaTime)
        stage.draw()
    }

    override fun onTickEntity(entity: Entity) {
    }
}