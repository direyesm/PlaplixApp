package com.example.plaplixapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plaplixapp.model.local.adapters.CellAdapter
import com.example.plaplixapp.model.local.entities.PlapixEnti
import com.example.plaplixapp.model.vmPlaplixCell.CellVM
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() , CellAdapter.cellData{

    lateinit var mViewModel: CellVM
    lateinit var mAdapter: CellAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(CellVM::class.java)
        mAdapter = CellAdapter(this)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = recyclerView
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL))

        mViewModel.liveDataFromLocal.observe(viewLifecycleOwner, Observer {
            mAdapter.updateAdapter(it)
        })


    }

    override fun passTheCell(cell: PlapixEnti) {
        val mBundle = Bundle()
        mBundle.putString("id", cell.id.toString())
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, mBundle)

    }
}