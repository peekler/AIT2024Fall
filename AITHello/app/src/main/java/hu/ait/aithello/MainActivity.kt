package hu.ait.aithello

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import hu.ait.aithello.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root) // root is the LinearLayout



        binding.btnShow.setOnClickListener {
            val textData = binding.etName.text.toString().toInt()

            Log.d("TAG_MAIN",
                "show button clicked $textData")

            //Toast.makeText(this,
            //    "hello toast $textData", Toast.LENGTH_LONG).show()

            Snackbar.make(binding.root,
                getString(R.string.text_snack), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.button_undo)) {
                    Toast.makeText(
                        this,
                        getString(R.string.text_undo_click), Toast.LENGTH_LONG
                    ).show()
                }
                .show()

            binding.tvData.text =
                "$textData ${Date(System.currentTimeMillis()).toString()}"

            revealCard()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun revealCard() {
        val x = binding.cardview.getRight()
        val y = binding.cardview.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(
            binding.cardview.getWidth().toDouble(),
            binding.cardview.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            binding.cardview,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        binding.cardview.setVisibility(View.VISIBLE)
        anim.duration = 3000 // milliseconds
        anim.start()
    }


}