package com.monash.monashuniapp.Views

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monash.monashuniapp.Models.BusList
import com.monash.monashuniapp.Models.CarParkList
import com.monash.monashuniapp.Models.LectureList
import com.monash.monashuniapp.R

class MainScreenAdapter(// Internal data
    private val lectureList: LectureList,
    private val carParkList: CarParkList,
    private val busList: BusList
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class CarParkViewHolder(itemView: View, private val cpList: CarParkList) :
        RecyclerView.ViewHolder(itemView) {
        // View for the carpark color codes.
        private val cpColorCodes: LinearLayout
        private val cpFreeSpaces: TextView

        // Internal color code array for the circles
        private val colorList =
            arrayOf("#0000FF", "#9933ff", "#cc00ff", "#ff0000", "#ffff66", "#00cc00", "#ff9900")

        init {

            // CarPark Stuff here
            cpColorCodes = itemView.findViewById(R.id.car_parks_color_codes)
            cpFreeSpaces = itemView.findViewById(R.id.car_parks_free_spaces)

            drawCarParkColorCodes()
            setFreeSpaces(cpList.response[0].freeSpace)
        }

        private fun drawCarParkColorCodes() {
            cpColorCodes.removeAllViews()

            for (i in 0..6) {
                val view = View(cpColorCodes.context)
                val lp = LinearLayout.LayoutParams(32, 32)
                lp.setMargins(0, 0, 8, 0)
                view.layoutParams = lp

                val shape = GradientDrawable()
                shape.shape = GradientDrawable.OVAL
                shape.setColor(Color.parseColor(colorList[i]))
                view.background = shape

                cpColorCodes.addView(view)
            }
        }

        private fun setFreeSpaces(freeSpaces: String) {
            cpFreeSpaces.text = freeSpaces
        }
    }


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