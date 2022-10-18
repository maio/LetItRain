package cz.maio.letitrain.component

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentHook
import com.github.quillraven.fleks.ComponentType

class ImageComponent : Component<ImageComponent> {
    override fun type(): ComponentType<ImageComponent> = ImageComponent

    companion object : ComponentType<ImageComponent>() {
        val onImageAdd: ComponentHook<ImageComponent> = { _, component ->
            inject<Stage>("GameStage").addActor(component.image)
        }

        val onImageRemove: ComponentHook<ImageComponent> = { _, component ->
            inject<Stage>("GameStage").root.removeActor(component.image)
        }
    }

    lateinit var image: Image
}