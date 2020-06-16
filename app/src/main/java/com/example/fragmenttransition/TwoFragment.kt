package com.example.fragmenttransition

import android.media.Image
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.*

class TwoFragment : Fragment() {

    lateinit var imageView: ImageView
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.two_fragment, container, false)
        imageView = view.findViewById(R.id.imageView)
        //postponeEnterTransition()
        //sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        //sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments

        if (arguments != null) {
            imageView.transitionName = arguments.getString("Transition Name")

            Glide.with(imageView)
                .load(arguments.getInt("text"))
                .into(imageView)
            //imageView.setImageResource(arguments.getInt("text"))

        }
    }

    companion object {
        private var mTextView: TextView? = null
    }
}
