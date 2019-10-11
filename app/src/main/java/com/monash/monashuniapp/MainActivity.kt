package com.monash.monashuniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.monash.monashuniapp.Models.UserPortal

class MainActivity : AppCompatActivity() {

    // If we were connecting to an API we would define our retrofit builder up here which pointed to
    // an interface which defines all our endpoints we want to access. The compositedisopsable would be defined here too.


    /* void getdata()
     * inside this function the requestinteface would be added, we would add the observable to the main thread, subscribeon IO scheduler and the subscribe callback would point to
     * our displayData() function.
     */

    private fun displayData() {
        // This structure would be more well defined in some API and the extra information could be used to create a more tailored experience
        val usr = UserPortal("Kier", "17/05 Tuesday, Week 8");

        // Set the relevant information in the actionbar
        supportActionBar?.setTitle("Hey, " + usr.userDisplayName)
        supportActionBar?.setSubtitle(usr.currentWeek)


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // At this point, we would display some loading screen and set up our observable to subscribe to the api transaction. Lets call this function getData()
        displayData()
    }

    // AB Specific
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_ab_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when (item.getItemId()) {
            R.id.ab_notification -> {
                Toast.makeText(this, "Notification bell pressed", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.ab_profile -> {
                Toast.makeText(this, "Profile Icon pressed", Toast.LENGTH_LONG).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)

        }
    }
}