package plenty.lucky.rotations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import plenty.lucky.rotations.databinding.ActivityPlugBinding

class PlugActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlugBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlugBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        val cardIntent = Intent(this, CardsActivity::class.java)

       binding.buttonEasy.setOnClickListener {
            startActivity(cardIntent.putExtra("difficulty", "easy"))
        }

        binding.buttonMedium.setOnClickListener {
            startActivity(cardIntent.putExtra("difficulty", "medium"))
        }

        binding.buttonHard.setOnClickListener {
            startActivity(cardIntent.putExtra("difficulty", "hard"))
        }
    }
}