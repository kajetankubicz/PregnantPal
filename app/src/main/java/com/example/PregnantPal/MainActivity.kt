package com.example.PregnantPal

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private var selectedTextView: TextView? = null
    lateinit var nameEditText: EditText
    lateinit var surnameEditText: EditText
    lateinit var phoneNumberEditText: EditText
    lateinit var saveButton: Button
    lateinit var whenDateEditText: TextView
    lateinit var howManyChildEditText: EditText
    lateinit var howTallEditText: EditText
    lateinit var howWeightEditText: EditText
    lateinit var howSmokeEditText: EditText
    lateinit var howDrinkEditText: EditText
    lateinit var whenExaminationEditText: TextView

    @RequiresApi(Build.VERSION_CODES.N)
    private val calendar = Calendar.getInstance()
    private val calendar2 = Calendar.getInstance()
    private val formatter = SimpleDateFormat("dd. MMM. yyyy", Locale.ENGLISH)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameEditText = findViewById(R.id.name_edit_text)
        surnameEditText = findViewById(R.id.surname_edit_text)
        phoneNumberEditText = findViewById(R.id.phone_edit_text)
        saveButton = findViewById(R.id.save_button)
        whenDateEditText = findViewById(R.id.whenBirth_edit_text)
        whenDateEditText.setOnClickListener {
            selectedTextView = whenDateEditText
            DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        howManyChildEditText = findViewById(R.id.childNum_edit_text)
        howTallEditText = findViewById(R.id.height_edit_text)
        howWeightEditText = findViewById(R.id.weight_edit_text)
        howSmokeEditText = findViewById(R.id.smoke_edit_text)
        howDrinkEditText = findViewById(R.id.drink_edit_text)
        whenExaminationEditText = findViewById(R.id.examination_edit_text)
        whenExaminationEditText.setOnClickListener {
            selectedTextView = whenExaminationEditText
            DatePickerDialog(
                this,
                this,
                calendar2.get(Calendar.YEAR),
                calendar2.get(Calendar.MONTH),
                calendar2.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val surname = surnameEditText.text.toString().trim()
            val phoneNumber = phoneNumberEditText.text.toString().trim()
            val whenDate = whenDateEditText.text.toString().trim()
            val howManyChild = howManyChildEditText.text.toString().trim()
            val height = howTallEditText.text.toString().trim()
            val weight = howWeightEditText.text.toString().trim()
            val drink = howDrinkEditText.text.toString().trim()
            val smoke = howSmokeEditText.text.toString().trim()
            val examination = whenExaminationEditText.text.toString().trim()

            if (name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty() || whenDate.isEmpty() || howManyChild.isEmpty() || height.isEmpty() || weight.isEmpty()  || drink.isEmpty()  || smoke.isEmpty()  || examination.isEmpty()) {
                Toast.makeText(this, "Please input all data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            saveDataToFile(name, surname, phoneNumber, whenDate, howManyChild, height, weight, drink, smoke, examination)
        }
    }

    private fun saveDataToFile(name: String, surname: String, phoneNumber: String, whenDate:String, howManyChild: String, height: String, weight: String, drink: String, smoke: String, examination: String) {
        val file = File(getExternalFilesDir(null), "user_data.txt")
        val fos = FileOutputStream(file, true)
        if(file.length() == 0L){
            fos.write("Name, Surname, Phone Number, BirthDate, Number of children, Height, Weight, Drink, Smoke, Last examination \n".toByteArray())
        }
        fos.write("$name, $surname, $phoneNumber, $whenDate, $howManyChild, $height, $weight, $drink, $smoke, $examination\n".toByteArray())
        fos.close()
        Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val cal = Calendar.getInstance()
        cal.set(year, month, day)
        selectedTextView?.text = formatter.format(cal.time)
    }

}