package cz.maio.letitrain.component

class PlayerComponent(
    val keysDown: MutableSet<PlayerInput> = HashSet()
) {
    fun hasInput(playerInput: PlayerInput) = playerInput in keysDown
}