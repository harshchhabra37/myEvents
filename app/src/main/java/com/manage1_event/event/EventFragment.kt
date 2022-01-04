package com.manage1_event.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.manage1_event.event.database.BookingTable
import com.manage1_event.event.database.EventDB
import com.manage1_event.event.database.EventTable
import com.manage1_event.event.databinding.FragmentEventBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

private const val _id = "id"

class EventFragment : Fragment(), View.OnClickListener {
    private var id: Int? = null

    private var _db : EventDB? = null
    private val db get() = _db!!

    private var _binding : FragmentEventBinding? = null
    private val binding get()= _binding!!

    private var _event : EventTable? = null
    private val event get() = _event!!

    private lateinit var auth: FirebaseAuth
    private var user : FirebaseUser ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(_id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEventBinding.inflate(inflater, container, false)
        _db = EventDB.getInstance(container!!.context)
        _event = db.getDao().getEvent(id!!)
        auth = FirebaseAuth.getInstance()

        bindData()

        binding.bookBtn.setOnClickListener(this)


        return binding.root
    }

    private fun bindData() {
        binding.nameTv.text = event.name
        binding.costTv.text = "Rs. " + event.cost.toString()
        binding.noOfSeatsTv.text = event.seatsLeft.toString()
        binding.descTv.text = event.desc
        binding.placeTv.text = event.place
        binding.dateTv.text = event.date

        user = auth.currentUser

        checkSeats()
        checkEvent()
        checkDate()
        if (user != null) {
            checkBooking(user!!.email!!)
        }

    }

    private fun checkDate() {
        if(SimpleDateFormat("dd-MM-yyyy").parse(event.date).before(Date())){
            binding.bookBtn.isEnabled = false
            binding.bookBtn.text = "PAST"
        }
    }

    private fun checkBooking(email : String) {
        if(db.getBookingDao().getEmail(id!!)==email){
            binding.bookBtn.isEnabled = false
            binding.bookBtn.text = "BOOKED"
        }
    }

    private fun checkEvent() {
        if(event.seatsLeft==0){
            binding.bookBtn.isEnabled = false
            binding.bookBtn.text = "BOOKED"
        }

    }

    private fun checkSeats() {
        if(event.seatsLeft in 1..9)
            binding.warningTv.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            EventFragment().apply {
                arguments = Bundle().apply {
                    putString(_id, id)
                }
            }
    }

    override fun onClick(p0: View?) {
        db.getDao().bookEvent(event.id)
        _event = db.getDao().getEvent(id!!)

        lifecycleScope.launch(Dispatchers.IO) {
            db.getBookingDao().insert(BookingTable(event.id, user!!.email!!))
        }
        binding.bookBtn.isEnabled = false
        binding.bookBtn.text = "BOOKED"

        bindData()
    }
}