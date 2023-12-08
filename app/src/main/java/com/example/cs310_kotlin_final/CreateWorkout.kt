package com.example.cs310_kotlin_final

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class CreateWorkout: AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var editTextReps: EditText
    lateinit var radioGroupComplete: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_workout_layout)

        editTextName = findViewById(R.id.editTextWorkoutName)
        editTextReps = findViewById(R.id.editTextNumberReps)
        radioGroupComplete = findViewById(R.id.radioGroupDone)

        var addButton: Button = findViewById(R.id.buttonAdd)
        addButton.setOnClickListener{
            val name = editTextName.text.toString()
            val reps = editTextReps.text.toString()
            if(name != "" && reps != "" && reps.toInt() > 0){
                var completed: String = ""
                when(radioGroupComplete.checkedRadioButtonId){
                    R.id.radioButtonComplete -> completed = "Completed"
                    R.id.radioButtonIncomplete -> completed = "Incomplete"
                }
                val newWorkout = Workout(name, reps.toInt(), completed)
                MainActivity.Workouts.add(newWorkout)
                MainActivity.itemAdapter.notifyDataSetChanged()
                finish()
            }else{
                val toast = Toast.makeText(this, "please fill in name and reps", Toast.LENGTH_LONG).show()
            }
        }
    }
}
