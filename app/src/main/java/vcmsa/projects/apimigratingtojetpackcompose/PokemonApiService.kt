package vcmsa.projects.apimigratingtojetpackcompose

import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemon?limit=20")
    suspend fun getPokemonList(): PokemonResponse
}