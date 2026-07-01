package com.example.animation

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.animation.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animView = binding.animationView

        animView.post {
            animView.pivotX = 0.5f * animView.width
            animView.pivotY = 1f * animView.height
        }

        val animator = ObjectAnimator.ofFloat(animView, "rotationX", 60f).apply {
            duration = 2000
        }

        binding.btnFall.setOnClickListener {
            if (animView.rotationX >= 60f) return@setOnClickListener
            animView.clearAnimation()
            animView.rotationX = 0f
            animView.rotationY = 0f
            animator.start()
        }

        binding.btnReset.setOnClickListener {
            if (animView.rotationX < 60f) return@setOnClickListener
            val resetAnim = ObjectAnimator.ofFloat(animView, "rotationX", animView.rotationX, 0f).apply {
                duration = 1000
            }
            resetAnim.start()
        }
    }
}