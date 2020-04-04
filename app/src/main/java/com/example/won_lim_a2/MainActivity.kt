package com.example.won_lim_a2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.app.AlertDialog
import android.widget.RadioButton
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var playerPosition:String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.myToolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.about_page -> {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("About")
                builder.setMessage("App was built by Yunna Won and Shannon Lim")

                builder.setNegativeButton("Ok") { dialog, which ->
                    Toast.makeText(applicationContext,
                        "Thanks for looking at About Page", Toast.LENGTH_SHORT).show()
                }

                builder.show()
                return true
            }else -> return super.onOptionsItemSelected(item)
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_goalie ->
                    if (checked) {
                        playerPosition="goalie"
                    }
                R.id.radio_defence ->
                    if (checked) {
                        playerPosition="defence"
                    }
                R.id.radio_forward ->
                    if (checked) {
                        playerPosition="forward"
                    }
            }
        }
    }

   fun saveRecord(view: View){
       val name = etPlayerName.text.toString()
       val goals = etGoals.text.toString()

       val dbHandler: DatabaseHandler = DatabaseHandler(this)

       if(name.trim()!="" && playerPosition!="" && goals.trim()!=""){
           var numGoals=0;
        try{
            numGoals=goals.toInt();
       }catch (e: IllegalFormatConversionException){
           val myToast = Toast.makeText(getApplicationContext(),"Must be a number", Toast.LENGTH_SHORT)
           myToast.show();
       }

           val status = dbHandler.addPlayer(PlayerModelClass(playerName = name, playerPosition = playerPosition, playerGoals = numGoals))

           if(status>1){
               Toast.makeText(applicationContext,"Saved Successfully",Toast.LENGTH_LONG).show()
               etPlayerName.text.clear()
               etGoals.text.clear()

           }else{
               Toast.makeText(applicationContext,"unable to save to sqlite database",Toast.LENGTH_LONG).show()
           }

       }else{
           Toast.makeText(applicationContext,"name or goals cannot be blank and position must be selected",Toast.LENGTH_LONG).show()
       }
   }

}
