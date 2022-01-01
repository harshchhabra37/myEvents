package com.manage1_event.event

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.manage1_event.event.database.EventDB
import com.manage1_event.event.database.EventTable
import com.manage1_event.event.databinding.FragmentListBinding
import com.manage1_event.event.eventlist.EventAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragmentViewModel(application:Application):AndroidViewModel(application) {
    val db = EventDB.getInstance(application)!!



    fun makeTable(){
        val event1 = EventTable(name = "Birthday Party", date = "25-03-2022", seatsLeft = 100, desc = "Bring Your own Beer Birthday Party for my friend come and chill with us", place = "Delhi", cost = 1000)
        val event2 = EventTable(name = "Concert", date = "04-06-2022", seatsLeft = 5, desc = "Private Concert by AP Dhillon for very few people ", place = "Vijayawada", cost = 30000)
        val event3 = EventTable(name = "Standup Comedy", date = "02-03-2022", seatsLeft = 40, desc = "Standup Comedy by Basu on his tour throughout major cities in India", place = "Delhi", cost = 5000)
        val event4 = EventTable(name = "Poolside Party", date = "25-03-2021", seatsLeft = 67, desc = "Pool House Party with snacks DJ and paid drinks", place = "Mumbai", cost = 7000)
        val event5 = EventTable(name = "Funeral", date = "25-03-2022", seatsLeft = 0, desc = "Funeral for a very accomplished writer/Activist, all are welcome to pay their respects", place = "Kanpur", cost = 500)
        val event6 = EventTable(name = "Circket Match", date = "25-07-2021", seatsLeft = 0, desc = "Watch Cricket match along with other cricket fans and enthusiasts", place = "Chandigarh", cost = 700)

        viewModelScope.launch(Dispatchers.IO){
            if(db.getDao().read().count()!=6) {
                db?.getDao()?.insert(event = event1)
                db?.getDao()?.insert(event = event2)
                db?.getDao()?.insert(event = event3)
                db?.getDao()?.insert(event = event4)
                db?.getDao()?.insert(event = event5)
                db?.getDao()?.insert(event = event6)
            }
        }

}
}