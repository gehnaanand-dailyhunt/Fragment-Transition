package com.example.fragmenttransition

import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.view.Menu
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), OneFragment.OneFragmentListener {
    var fragment = OneFragment()
    private val MOVE_DEFAULT_TIME: Long = 1000
    private val FADE_DEFAULT_TIME: Long = 300
    lateinit var fragmentManager: FragmentManager
    val TAG = "Current Fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        ft.add(R.id.place_holder, fragment, TAG)
        ft.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.two_fragments, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onButtonClick(text: Int, imageView: ImageView) {
        val prevFrag = fragmentManager.findFragmentByTag(TAG)

        val fragment = TwoFragment()
        val arguments = Bundle()
        arguments.putInt("text", text)
        arguments.putString("Transition Name", ViewCompat.getTransitionName(imageView))
        fragment.arguments = arguments

        var ft = fragmentManager.beginTransaction()
        val exitFade = Fade()
        exitFade.duration = 800
        if (prevFrag != null) {
            prevFrag.exitTransition = exitFade
        }

        val enterTransitionSet = TransitionSet()
        enterTransitionSet.addTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.move))
        enterTransitionSet.duration = MOVE_DEFAULT_TIME
        //enterTransitionSet.startDelay = FADE_DEFAULT_TIME
        fragment.sharedElementEnterTransition = enterTransitionSet

        val enterFade = Fade()
        //enterFade.startDelay = MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME
        enterFade.duration = FADE_DEFAULT_TIME
        fragment.enterTransition = enterFade
        //ft.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit,
        //R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit)
        ViewCompat.getTransitionName(imageView)?.let { ft.addSharedElement(imageView, it) }
        ft.replace(R.id.place_holder, fragment, TAG)
        ft.addToBackStack(null)
        ft.commitAllowingStateLoss()
    }
}