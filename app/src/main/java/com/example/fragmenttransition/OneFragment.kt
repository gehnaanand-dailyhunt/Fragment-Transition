package com.example.fragmenttransition

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.*

class OneFragment : Fragment() {
    var activityCallback: OneFragmentListener? = null
    lateinit var adapter: CustomeAdapter
    lateinit var recyclerView: RecyclerView
    //Listener for onButtonClick UI
    interface OneFragmentListener {
        fun onButtonClick(text: Int)
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

    //We get the reference to the editText and the button setUp the OnClickListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.one_fragment, container, false)
        /*mEditText =
            view.findViewById<View>(R.id.textOneFragment) as EditText
        val button =
            view.findViewById<View>(R.id.buttonChange) as Button
        button.setOnClickListener { v -> buttonClicked(v) }*/
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

        adapter.setOnClickListener {
            activityCallback?.onButtonClick(it)
        }

    }
    companion object {
        private var mEditText: EditText? = null
    }
}