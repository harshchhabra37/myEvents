package com.manage1_event.event

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.manage1_event.event.database.EventDB
import com.manage1_event.event.database.EventTable
import com.manage1_event.event.databinding.FragmentListBinding
import com.manage1_event.event.eventlist.EventAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragment : Fragment(), EventAdapter.ButtonClicked {
    //private var _db : EventDB? = null
    //private val db get() = _db!!

    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ListFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel=ViewModelProvider(this).get(ListFragmentViewModel::class.java)

       lifecycleScope.launch(Dispatchers.IO) {
           viewModel.makeTable()

       }




        val adapter=setAdapter()

        viewModel.allNotes.observe(viewLifecycleOwner, Observer{list->
            list?.let{
                adapter.updateList(list)
            }
        })


        return binding.root
    }


    private fun setAdapter(): EventAdapter {
        //var adapter = EventAdapter(db.getDao().read(), this)
        //viewModel.db.getAllEvents()
        var adapter= EventAdapter(this)

        binding.rv.setHasFixedSize(true)
        binding.rv.adapter = adapter

        return adapter
    }

   // private fun getRoomDbInstance(context : Context) = EventDB.getInstance(context)

    override fun onButtonClicked(event: EventTable) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToEventFragment(event.id))
    }

}