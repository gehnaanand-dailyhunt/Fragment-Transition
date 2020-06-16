package com.example.fragmenttransition

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.*

class OneFragment : Fragment() {
    var activityCallback: OneFragmentListener? = null
    lateinit var adapter: CustomeAdapter
    lateinit var recyclerView: RecyclerView

    interface OneFragmentListener {
        fun onButtonClick(text: Int, imageView: ImageView)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        activityCallback = try {
            activity as OneFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                activity.toString()
                        + " must implement OneFragmentListener"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.one_fragment, container, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            //sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        }
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList = ArrayList<Int>()
        arrayList.add(R.drawable.ic_launcher_foreground)
        arrayList.add(R.drawable.ic_action_bike)

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = CustomeAdapter()
        adapter.submitList(arrayList)

        recyclerView.adapter = adapter

        adapter.setOnClickListener {i, j ->
            activityCallback?.onButtonClick(i, j)
        }

    }
}