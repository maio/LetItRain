package cz.maio.letitrain.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.github.quillraven.fleks.world
import cz.maio.letitrain.component.ImageComponent
import cz.maio.letitrain.component.ImageComponent.Companion.ImageComponentListener
import cz.maio.letitrain.input.PlayerInputProcessor
import cz.maio.letitrain.system.RenderSystem
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile

class GameScreen : KtxScreen {
    private val batch = SpriteBatch()
    private val gameStage = Stage(ExtendViewport(16f, 9f), batch)
    private val logo = Texture("logo.png".toInternalFile(), true).apply {
        setFilter(
            Texture.TextureFilter.Linear,
            Texture.TextureFilter.Linear
        )
    }

    private val eWorld = world {
        injectables {
            add("GameStage", gameStage)
        }

        components {
            add<ImageComponentListener>()
        }

        systems {
            add<RenderSystem>()
        }
    }

    init {
        val logoActor = Image(logo).apply {
            setScaling(Scaling.fit)
            setSize(1f, 1f)
            setPosition(0.1f, 0.1f)
        }

        Gdx.input.inputProcessor = PlayerInputProcessor()

        eWorld.entity {
            add<ImageComponent> {
                image = logoActor
            }
        }
    }

    override fun render(delta: Float) {
        clearScreen(red = 75 / 255f, green = 139 / 255f, blue = 171 / 255f)
        eWorld.update(delta)
    }

    override fun dispose() {
        gameStage.disposeSafely()
        logo.disposeSafely()
        batch.disposeSafely()
    }
}
