package plenty.lucky.rotations

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import plenty.lucky.rotations.databinding.ActivityCardsBinding
import plenty.lucky.rotations.plug.EasyCardFragment
import plenty.lucky.rotations.plug.HardCardFragment
import plenty.lucky.rotations.plug.MediumCardFragment

class CardsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCardsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardsBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        val difficulty = intent?.extras?.getString("difficulty")
        createCards(difficulty)
    }

    private fun createCards(difficulty: String?) {
        val fgManager = supportFragmentManager

        when (difficulty) {
            "easy" -> {
                val easyCardFragment = EasyCardFragment()
                val fgTransaction = fgManager.beginTransaction()
                fgTransaction.add(R.id.fragment_cards_activity, easyCardFragment)
                fgTransaction.commit()
            }
            "medium" -> {
                val mediumCardFragment = MediumCardFragment()
                val fgTransaction = fgManager.beginTransaction()
                fgTransaction.add(R.id.fragment_cards_activity, mediumCardFragment)
                fgTransaction.commit()
            }
            "hard" -> {
                val hardCardFragment = HardCardFragment()
                val fgTransaction = fgManager.beginTransaction()
                fgTransaction.add(R.id.fragment_cards_activity, hardCardFragment)
                fgTransaction.commit()
            }
        }
    }
}