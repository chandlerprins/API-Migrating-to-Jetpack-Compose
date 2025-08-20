package vcmsa.projects.apimigratingtojetpackcompose

data class PokemonResponse(
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
) {
    // Extract Pokémon ID from the URL
    val id: String
        get() = url.split("/").dropLast(1).last()

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}