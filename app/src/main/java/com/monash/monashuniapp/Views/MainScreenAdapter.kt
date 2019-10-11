package com.monash.monashuniapp.Views

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monash.monashuniapp.Models.BusList
import com.monash.monashuniapp.Models.CarParkList
import com.monash.monashuniapp.Models.LectureList

class MainScreenAdapter(// Internal data
    private val lectureList: LectureList,
    private val carParkList: CarParkList,
    private val busList: BusList
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return null;
    }


    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    // Different view types
    override fun getItemViewType(position: Int): Int {
        if (position == TIMETABLE_POSITION)
            return TIMETABLE_ID
        if (position == CARPARK_POSITION)
            return CARPARK_ID
        return if (position == BUS_POSITION)
            BUS_ID
        else
            UNKNOWN_ID
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return MAX_VIEW_TYPES
    }

    companion object {

        // Position for the cards
        private val TIMETABLE_POSITION = 0
        private val CARPARK_POSITION = 1
        private val BUS_POSITION = 2

        private val MAX_VIEW_TYPES = 3

        // Layout ID's
        private val TIMETABLE_ID = 0
        private val CARPARK_ID = 1
        private val BUS_ID = 2
        private val UNKNOWN_ID = 3
    }
}