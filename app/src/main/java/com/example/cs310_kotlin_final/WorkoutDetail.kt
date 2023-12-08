package com.example.cs310_kotlin_final

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WorkoutDetail:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workout_detail_layout)

        var textViewName:EditText = findViewById(R.id.editTextDetailName)
        var textViewReps:EditText = findViewById(R.id.editTextDetailReps)
        var radioButtonComplete:RadioButton = findViewById(R.id.radioButtonDetComplete)
        var radioButtonIncomplete:RadioButton = findViewById(R.id.radioButtonDetIncomplete)
        var radioGroup:RadioGroup = findViewById(R.id.radioGroupDetail)
        var buttonUpdate:Button = findViewById(R.id.buttonDetUpdate)
        var buttonDelete:Button = findViewById(R.id.buttonDetDelete)

        val intent: Intent = intent
        val position = intent.getIntExtra("POSITION", 0)
        var workout:Workout = MainActivity.Workouts.get(position)
        //Toast.makeText(this, workout.toString(), Toast.LENGTH_LONG).show()

        textViewName.setText(workout.name)
        textViewReps.setText(workout.reps.toString())

        if(workout.complete == "Completed"){
            radioButtonComplete.isChecked = true
        }else if(workout.complete == "Incomplete"){
            radioButtonIncomplete.isChecked = true
        }

        buttonDelete.setOnClickListener {
            MainActivity.Workouts.removeAt(position)
            MainActivity.itemAdapter.notifyDataSetChanged()
            finish()
        }

        buttonUpdate.setOnClickListener {
            var completed: String = ""
            if(textViewName.text.toString() != "" && textViewReps.text.toString() != "") {
                when (radioGroup.checkedRadioButtonId) {
                    R.id.radioButtonDetComplete -> completed = "Completed"
                    R.id.radioButtonDetIncomplete -> completed = "Incomplete"
                }
                var newWorkout: Workout = Workout(
                    textViewName.text.toString(),
                    textViewReps.text.toString().toInt(),
                    completed
                )
                MainActivity.Workouts.set(position, newWorkout)
                MainActivity.itemAdapter.notifyDataSetChanged()
                finish()
            }else{
                Toast.makeText(this, "please fill in name and reps", Toast.LENGTH_LONG).show()
            }
        }

    }

}