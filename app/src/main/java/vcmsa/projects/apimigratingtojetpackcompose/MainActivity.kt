package vcmsa.projects.apimigratingtojetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import vcmsa.projects.apimigratingtojetpackcompose.ui.theme.APIMigratingToJetpackComposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APIMigratingToJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFB3E5FC)
                ) {
                    PokemonListScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel) {
    val pokemonList by viewModel.pokemonList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC))
            .padding(8.dp)
    ) {
        // Header
        Text(
            text = "Poke Fun!!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFC107), // Pokémon Yellow
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        // List of Pokémon
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(pokemonList) { pokemon ->
                PokemonItem(pokemon)
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clickable {
                Toast
                    .makeText(context, "You chose ${pokemon.name.replaceFirstChar { it.uppercase() }}!", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(pokemon.imageUrl),
                contentDescription = pokemon.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2) // Pokémon Blue
                )
            )
        }
    }
}
