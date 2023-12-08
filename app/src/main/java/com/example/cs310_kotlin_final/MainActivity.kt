package com.example.cs310_kotlin_final


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cs310_kotlin_final.MainActivity.Companion.itemAdapter


class MainActivity : AppCompatActivity(), ItemAdapter.ItemClickListener{

    companion object {
        val Workouts = mutableListOf<Workout>()
        lateinit var itemAdapter: ItemAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewMainWorkout)
        itemAdapter = ItemAdapter(Workouts, this)
        recyclerView.adapter = itemAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainactivity_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.addItem -> {
                //Toast.makeText(this, "add", Toast.LENGTH_LONG).show()
                val intent = Intent(this, CreateWorkout::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(this, position.toString(), Toast.LENGTH_LONG).show()
        val intent = Intent(this, WorkoutDetail::class.java)
        intent.putExtra("POSITION", position)
        startActivity(intent)
    }


}