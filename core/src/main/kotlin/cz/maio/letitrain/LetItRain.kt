package cz.maio.letitrain

import cz.maio.letitrain.screen.GameScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen

class LetItRain : KtxGame<KtxScreen>() {
    override fun create() {
        addScreen(GameScreen())
        setScreen<GameScreen>()
    }
}

