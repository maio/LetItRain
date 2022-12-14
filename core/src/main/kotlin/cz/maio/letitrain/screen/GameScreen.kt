package cz.maio.letitrain.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.github.quillraven.fleks.world
import cz.maio.letitrain.component.*
import cz.maio.letitrain.component.ImageComponent.Companion.onImageAdd
import cz.maio.letitrain.component.ImageComponent.Companion.onImageRemove
import cz.maio.letitrain.input.PlayerInputProcessor
import cz.maio.letitrain.system.*
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
    private val player2Texture = Texture("helicopter_2.png".toInternalFile(), true).apply {
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
            onAdd(ImageComponent, onImageAdd)
            onRemove(ImageComponent, onImageRemove)
        }

        systems {
            add(PlayerMovementSystem())
            add(MovementSystem())
            add(DropletSpawningSystem())
            add(DropletCollisionSystem())
            add(RenderingSystem())
        }
    }

    init {
        Gdx.input.inputProcessor = PlayerInputProcessor(eWorld)

        // Spawn player 1
        eWorld.entity {
            it += Player1Component()
            it += PlayerComponent()
            it += MoveComponent()
            it += ImageComponent().apply {
                image = Image(player1Texture).apply {
                    setScaling(Scaling.fit)
                    setSize(1f, 1f)
                    setPosition((gameStage.viewport.worldWidth / 2) - 0.5f, 0.1f)
                }
            }
        }

        // Spawn player 2
        eWorld.entity {
            it += Player2Component()
            it += PlayerComponent()
            it += MoveComponent()
            it += ImageComponent().apply {
                image = Image(player2Texture).apply {
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
