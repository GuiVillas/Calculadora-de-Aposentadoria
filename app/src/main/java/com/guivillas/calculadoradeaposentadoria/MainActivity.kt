package com.guivillas.calculadoradeaposentadoria

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.guivillas.calculadoradeaposentadoria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = binding.spinner
        val editText = binding.editText
        val textView = binding.textView

        val options = listOf("Masculino", "Feminino")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        binding.spinner.adapter = adapter

        binding.button.setOnClickListener {
            val selectedOption = spinner.selectedItem as String
            val age = editText.text.toString().toLongOrNull()
            var result: Long = 0

            if (selectedOption.trim() == "Masculino" && age != null) {
                result = 65 - age
            } else if(selectedOption.trim() == "Feminino" && age != null) {
                result = 62 - age
            } else {
                textView.text = "Informe uma idade válida."
            }

            if (result <= 0 && age != null) {
                textView.text = "Você já deveria estar aposentado."
            } else if (age != null){
                textView.text = "Faltam $result anos para você se aposentar."
            }
        }
    }
}