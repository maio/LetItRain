package cz.maio.letitrain

import cz.maio.letitrain.screen.FirstScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen

class LetItRain : KtxGame<KtxScreen>() {
    override fun create() {
        addScreen(FirstScreen())
        setScreen<FirstScreen>()
    }
}

