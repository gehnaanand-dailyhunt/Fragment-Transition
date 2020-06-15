package com.example.fragmenttransition

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OneFragment.OneFragmentListener {
    var fragment = OneFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.place_holder, fragment)
        ft.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.two_fragments, menu)
        return true
    }

    override fun onButtonClick(text: Int) {
        val fragment = TwoFragment()
        val arguments = Bundle()
        arguments.putInt("text", text)
        fragment.arguments = arguments

        var ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit,
        R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit)
        ft.replace(R.id.place_holder, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }
}