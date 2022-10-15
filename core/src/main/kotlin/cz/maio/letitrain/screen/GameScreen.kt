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
import cz.maio.letitrain.component.MoveComponent
import cz.maio.letitrain.component.PlayerComponent
import cz.maio.letitrain.input.PlayerInputProcessor
import cz.maio.letitrain.system.DropletSpawningSystem
import cz.maio.letitrain.system.MovementSystem
import cz.maio.letitrain.system.PlayerMovementSystem
import cz.maio.letitrain.system.RenderingSystem
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile

class GameScreen : KtxScreen {
    private val batch = SpriteBatch()
    private val gameStage = Stage(ExtendViewport(16f, 9f), batch)
    private val player1Texture = Texture("helicopter.png".toInternalFile(), true).apply {
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
            add<PlayerMovementSystem>()
            add<MovementSystem>()
            add<DropletSpawningSystem>()
            add<RenderingSystem>()
        }
    }

    init {
        Gdx.input.inputProcessor = PlayerInputProcessor(eWorld)

        // Spawn player 1
        eWorld.entity {
            add<PlayerComponent>()
            add<MoveComponent>()
            add<ImageComponent> {
                image = Image(player1Texture).apply {
                    setScaling(Scaling.fit)
                    setSize(1f, 1f)
                    setPosition((gameStage.viewport.worldWidth / 2) - 0.5f, 0.1f)
                }
            }
        }
    }

    override fun render(delta: Float) {
        clearScreen(red = 187 / 255f, green = 224 / 255f, blue = 242 / 255f)
        eWorld.update(delta)
    }

    override fun dispose() {
        gameStage.disposeSafely()
        player1Texture.disposeSafely()
        batch.disposeSafely()
    }
}
