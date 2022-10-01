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
import cz.maio.letitrain.system.DropletSpawnSystem
import cz.maio.letitrain.system.MoveSystem
import cz.maio.letitrain.system.RenderSystem
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import kotlin.random.Random

class GameScreen : KtxScreen {
    private val batch = SpriteBatch()
    private val gameStage = Stage(ExtendViewport(16f, 9f), batch)
    private val playerTexture = Texture("helicopter.png".toInternalFile(), true).apply {
        setFilter(
            Texture.TextureFilter.Linear,
            Texture.TextureFilter.Linear
        )
    }
    private val dropletTexture = Texture("droplet.png".toInternalFile(), true).apply {
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
            add<MoveSystem>()
            add<DropletSpawnSystem>()
            add<RenderSystem>()
        }
    }

    init {
        Gdx.input.inputProcessor = PlayerInputProcessor(eWorld)

        eWorld.entity {
            add<ImageComponent> {
                image = Image(playerTexture).apply {
                    setScaling(Scaling.fit)
                    setSize(1f, 1f)
                    setPosition((gameStage.viewport.worldWidth / 2) - 0.5f, 0.1f)
                }
            }
            add<MoveComponent>()
            add<PlayerComponent>()
        }

//        spawnDroplet()
//        spawnDroplet()
//        spawnDroplet()
    }

    private fun spawnDroplet() {
        eWorld.entity {
            add<ImageComponent> {
                image = Image(dropletTexture).apply {
                    setScaling(Scaling.fit)
                    setSize(0.5f, 0.5f)
                    setPosition((gameStage.viewport.worldWidth / 2) - 0.5f, 6f)
                }
                image.y = gameStage.height
                image.x = Random.nextFloat() * gameStage.width
            }
            add<MoveComponent> {
                dy = -0.3f
            }
        }
    }

    override fun render(delta: Float) {
        // 187, 224, 242
        clearScreen(red = 187 / 255f, green = 224 / 255f, blue = 242 / 255f)
        eWorld.update(delta)
    }

    override fun dispose() {
        gameStage.disposeSafely()
        playerTexture.disposeSafely()
        batch.disposeSafely()
    }
}
