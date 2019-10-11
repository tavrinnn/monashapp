package com.monash.monashuniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // If we were connecting to an API we would define our retrofit builder up here which pointed to
    // an interface which defines all our endpoints we want to access. The compositedisopsable would be defined here too.


    /* void getdata()
     * inside this function the requestinteface would be added, we would add the observable to the main thread, subscribeon IO scheduler and the subscribe callback would point to
     * our displayData() function.
     */



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // At this point, we would display some loading screen and set up our observable to subscribe to the api transaction. Lets call this function getData()
    }
}
