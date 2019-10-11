package com.monash.monashuniapp.Models

import java.util.*
import kotlin.collections.ArrayList

class APILayer {

    // List of lectures from the 'api' after reflection
    val lectureList: LectureList
        get() {
            val lectures = ArrayList<Lecture>()

            // We could also randomise the start time and end time ranges but it seems pretty irrelevant if we could instead just plug all this into an api
            for (i in 0 until getRandUpper(5))
                lectures.add(
                    Lecture(
                        "9:00 AM",
                        "12:00 AM",
                        "FIT1031 Lecture 01",
                        "Arun Kongaurthu",
                        "S4, 13 College Walk, Clayton"
                    )
                )

            return LectureList(lectures)
        }

    // Not too sure if the carpark actually contains a list of every carpark around the campus or whether this is just meant to be
    // a running total of the total number of free spaces across the campus? It can be tweaked later if required.
    val carParkList: CarParkList
        get() {
            val cp = CarPark("894")
            val carParkList = ArrayList<CarPark>()
            carParkList.add(cp)
            return CarParkList(carParkList)
        }

    // List of busses from the 'api' after reflection
    val busList: BusList
        get() {
            val busses = ArrayList<Bus>()

            for (i in 0 until getRandUpper(5))
                busses.add(Bus("Clayton", "Caulfield", "3 Min"))

            return BusList(busses)
        }

    // Minimum 3 values
    private fun getRandUpper(upperBand: Int): Int {
        return Random().nextInt(upperBand) + 3
    }

}