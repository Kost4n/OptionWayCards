package plenty.lucky.rotations.plug

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import plenty.lucky.rotations.PlugActivity
import plenty.lucky.rotations.R
import plenty.lucky.rotations.databinding.FragmentCardsMediumBinding

class MediumCardFragment: Fragment() {
    private var _binding: FragmentCardsMediumBinding? = null
    private val binding get() = _binding!!

    lateinit var flip_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    var isfront = true

    private val almazPNG = R.drawable.almaz
    private var almazCount = 0
    private val rubinPNG = R.drawable.rubin
    private var rubinCount = 0
    private val izumrudPNG = R.drawable.izumrud
    private var izumrudCount = 0
    private val monetaPNG = R.drawable.moneta
    private var monetaCount = 0

    var isTwoBack = 0
    private var arrayBackImageViews: MutableList<ImageView> = arrayListOf()
    private var arrayFrontImageView: MutableMap<ImageView, Boolean> = mutableMapOf()
    private var guessed = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardsMediumBinding.inflate(inflater, container, false)

        setBackImage(binding.image1Back)
        setBackImage(binding.image2Back)
        setBackImage(binding.image3Back)
        setBackImage(binding.image4Back)
        setBackImage(binding.image5Back)
        setBackImage(binding.image6Back)
        setBackImage(binding.image7Back)
        setBackImage(binding.image8Back)
        arrayFrontImageView[binding.image1Front] = true
        arrayFrontImageView[binding.image2Front] = true
        arrayFrontImageView[binding.image3Front] = true
        arrayFrontImageView[binding.image4Front] = true
        arrayFrontImageView[binding.image5Front] = true
        arrayFrontImageView[binding.image6Front] = true
        arrayFrontImageView[binding.image7Front] = true
        arrayFrontImageView[binding.image8Front] = true

        binding.image1Front.setOnClickListener {
            createFrontToBackAnim(it as ImageView)
        }
        binding.image2Front.setOnClickListener {
            createFrontToBackAnim(it as ImageView)
        }
        binding.image3Front.setOnClickListener {
            createFrontToBackAnim(it as ImageView)
        }
        binding.image4Front.setOnClickListener {
            createFrontToBackAnim(it as ImageView)
        }
        binding.image5Front.setOnClickListener {
            createFrontToBackAnim(it as ImageView)
        }
        binding.image6Front.setOnClickListener {
            createFrontToBackAnim(it as ImageView)
        }
        binding.image7Front.setOnClickListener {
            createFrontToBackAnim(it as ImageView)
        }
        binding.image8Front.setOnClickListener {
            createFrontToBackAnim(it as ImageView)
        }

        return binding.root
    }

    private fun setBackImage(backImageView: ImageView) {
        when ((0..3 ).random()) {
            0 -> {
                if (almazCount != 2) {
                    backImageView.setImageResource(almazPNG)
                    almazCount++
                } else {
                    setBackImage(backImageView)
                }
            }
            1 -> {
                if (rubinCount != 2) {
                    backImageView.setImageResource(rubinPNG)
                    rubinCount++
                } else {
                    setBackImage(backImageView)
                }
            }
            2 -> {
                if (izumrudCount != 2) {
                    backImageView.setImageResource(izumrudPNG)
                    izumrudCount++
                } else {
                    setBackImage(backImageView)
                }
            }
            3 -> {
                if (monetaCount != 2) {
                    backImageView.setImageResource(monetaPNG)
                    monetaCount++
                } else {
                    setBackImage(backImageView)
                }
            }
        }
    }

    private fun createFrontToBackAnim(frontImageView: ImageView) {
        if (isTwoBack == 2) {
            Log.d("isTwoBack", "${isTwoBack == 2}")
            if (arrayBackImageViews[0].drawable.constantState == arrayBackImageViews[1].drawable.constantState) {
                isTwoBack = 0
                guessed++
                guessed++
                arrayBackImageViews = arrayListOf()
                createFrontToBackAnim(frontImageView)
            } else {
                isTwoBack = 0
                createBackToFrontAnim(arrayBackImageViews[0])
                createBackToFrontAnim(arrayBackImageViews[1])
                arrayFrontImageView[binding.root.getViewById(arrayBackImageViews[0].id + 1) as ImageView] = true
                arrayFrontImageView[binding.root.getViewById(arrayBackImageViews[1].id + 1) as ImageView] = true
                arrayBackImageViews = arrayListOf()
                createFrontToBackAnim(frontImageView)
            }
        } else if (arrayFrontImageView[frontImageView] == true){
            val scale = context?.applicationContext?.resources?.displayMetrics?.density
            frontImageView.cameraDistance = scale!! * 8000
            val backImageView = binding.root.getViewById(frontImageView.id - 1)
            backImageView.cameraDistance = scale * 8000

            arrayBackImageViews.add(backImageView as ImageView)

            flip_anim = AnimatorInflater.loadAnimator(
                context?.applicationContext,
                R.animator.rotation
            ) as AnimatorSet
            back_anim = AnimatorInflater.loadAnimator(
                context?.applicationContext,
                R.animator.back_flip
            ) as AnimatorSet

            isfront = if (isfront) {
                flip_anim.setTarget(frontImageView)
                back_anim.setTarget(backImageView)
                flip_anim.start()
                back_anim.start()
                isTwoBack++
                false
            } else {
                flip_anim.setTarget(frontImageView)
                back_anim.setTarget(backImageView)
                back_anim.start()
                flip_anim.start()
                isTwoBack++
                true
            }
            arrayFrontImageView[frontImageView] = false
        } else {
            if (guessed > 7) {
                win()
            }
        }
    }

    private fun createBackToFrontAnim(backImageView: ImageView) {
        val scale = context?.applicationContext?.resources?.displayMetrics?.density
        backImageView.cameraDistance = scale!! * 8000
        val frontImageView = binding.root.getViewById(backImageView.id + 1)
        frontImageView.cameraDistance = scale * 8000
        flip_anim = AnimatorInflater.loadAnimator(context?.applicationContext , R.animator.rotation) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(context?.applicationContext, R.animator.back_flip) as AnimatorSet
        isfront = if (isfront) {
            flip_anim.setTarget(backImageView)
            back_anim.setTarget(frontImageView)
            flip_anim.start()
            back_anim.start()
            false
        }else{
            flip_anim.setTarget(backImageView)
            back_anim.setTarget(frontImageView)
            back_anim.start()
            flip_anim.start()
            true
        }
    }

    private fun win() {
        val builder = AlertDialog.Builder(context)
            .setTitle("You win!")
            .setCancelable(true)
            .setPositiveButton("Play again") { _, _ ->
                startActivity(Intent(requireContext().applicationContext, PlugActivity::class.java))
            }
        builder.show()
    }
}