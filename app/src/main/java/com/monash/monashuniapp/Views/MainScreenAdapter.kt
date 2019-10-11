package com.monash.monashuniapp.Views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monash.monashuniapp.Models.*
import com.monash.monashuniapp.R

class MainScreenAdapter(// Internal data
    private val lectureList: LectureList,
    private val carParkList: CarParkList,
    private val busList: BusList
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class TimeTableViewHolder(itemView: View, private val llist: LectureList) :
        RecyclerView.ViewHolder(itemView) {
        private val ttList: ListView


        init {

            ttList = itemView.findViewById(R.id.timetable_lecture_list)
            ttList.adapter = TimeTableAdapter(itemView.context)
        }

        // Internal Adapter for the timetable
        private inner class TimeTableAdapter(private val context: Context) : BaseAdapter() {

            override fun getCount(): Int {
                return llist.response.size
            }

            override fun getItem(position: Int): Any {
                return llist.response[position]
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var convertView = convertView
                // inflate the layout for each list row
                if (convertView == null) {
                    convertView =
                        LayoutInflater.from(context).inflate(R.layout.timetable_row, parent, false)

                    // Tag the view id's so we don't unnecessarily look for them again in future if this object is not null...
                    val vhx = ViewHolder()
                    vhx.setIds(convertView!!)
                    convertView.tag = vhx
                }

                drawRow(convertView, llist.response[position])

                // returns the view for the current row
                return convertView
            }

            private fun drawRow(view: View, lecture: Lecture) {
                val vh = view.tag as ViewHolder
                vh.startTime!!.text = lecture.startTime
                vh.endTime!!.text = lecture.endTime
                vh.lectureTitle!!.text = lecture.lectureTitleName
                vh.lecturerName!!.text = lecture.lecturerName
                vh.lectureLocation!!.text = lecture.location
            }

            inner class ViewHolder {
                var startTime: TextView? = null
                var endTime: TextView? = null
                var lectureTitle: TextView? = null
                var lecturerName: TextView? = null
                var lectureLocation: TextView? = null

                internal fun setIds(target: View) {
                    startTime = target.findViewById(R.id.timetable_row_lecture_start_time)
                    endTime = target.findViewById(R.id.timetable_row_lecture_end_time)
                    lectureTitle = target.findViewById(R.id.timetable_row_lecture_title)
                    lecturerName = target.findViewById(R.id.timetable_row_lecture_mentor_name)
                    lectureLocation = target.findViewById(R.id.timetable_row_lecture_location)
                }
            }
        }
    }


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


    class BusViewHolder(itemView: View, private val bList: BusList) :
        RecyclerView.ViewHolder(itemView) {
        private val busList: ListView

        init {
            busList = itemView.findViewById(R.id.bus_list_main_list)
            busList.adapter = BusListAdapter(itemView.context)
        }


        // Internal Adapter for the timetable
        private inner class BusListAdapter(private val context: Context) : BaseAdapter() {

            override fun getCount(): Int {
                return bList.response.size
            }

            override fun getItem(position: Int): Any {
                return bList.response[position]
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var convertView = convertView
                // inflate the layout for each list row
                if (convertView == null) {
                    convertView =
                        LayoutInflater.from(context).inflate(R.layout.bus_row, parent, false)

                    // Tag the view id's so we don't unnecessarily look for them again in future if this object is not null...
                    val vhx = ViewHolder()
                    vhx.setIds(convertView!!)
                    convertView.tag = vhx
                }

                drawRow(convertView, bList.response[position])

                // returns the view for the current row
                return convertView
            }

            private fun drawRow(view: View, bus: Bus) {
                val vh = view.tag as ViewHolder
                vh.source!!.text = bus.from
                vh.destination!!.text = bus.to
                vh.eta!!.text = bus.eta
            }

            inner class ViewHolder {
                var source: TextView? = null
                var destination: TextView? = null
                var eta: TextView? = null

                internal fun setIds(target: View) {
                    source = target.findViewById(R.id.bus_row_source)
                    destination = target.findViewById(R.id.bus_row_destination)
                    eta = target.findViewById(R.id.bus_row_eta)
                }
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var vh: RecyclerView.ViewHolder? = null

        if (viewType == TIMETABLE_ID) {
            vh = TimeTableViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.timetable,
                    parent,
                    false
                ), lectureList
            )
        } else if (viewType == CARPARK_ID) {
            vh = CarParkViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.car_parks,
                    parent,
                    false
                ), carParkList
            )

        } else if (viewType == BUS_ID) {
            vh = BusViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.bus_list,
                    parent,
                    false
                ), busList
            )
        }

        return vh!!
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