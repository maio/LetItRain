package cz.maio.letitrain.screen

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.ExtendViewport
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile

class FirstScreen : KtxScreen {
    private val batch = SpriteBatch()
    private val gameStage = Stage(ExtendViewport(16f, 9f), batch)
    private val logo = Texture("logo.png".toInternalFile(), true).apply {
        setFilter(
            Texture.TextureFilter.Linear,
            Texture.TextureFilter.Linear
        )
    }

    init {
        gameStage.addActor(Image(logo).apply {
            setScaling(Scaling.fit)
            setSize(1f, 1f)
            setPosition(0.1f, 0.1f)
        })
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        gameStage.viewport.apply()
        gameStage.act()
        gameStage.draw()
    }

    override fun dispose() {
        gameStage.disposeSafely()
        logo.disposeSafely()
        batch.disposeSafely()
    }
}