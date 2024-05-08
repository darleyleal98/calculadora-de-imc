package com.darleyleal.calculadoraimc

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.darleyleal.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonConverter.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val peso = binding.editPeso.text.toString().toInt()
        val altura = binding.editAltura.text.toString().toFloat()

        if (view.id == R.id.button_converter) {
            binding.textResultado.text = verificarResultadoIMC(peso, altura)
        }
    }

    private fun verificarResultadoIMC(peso: Int, altura: Float): String {
        val resultado = peso / (altura * altura)
        when {
            resultado < 18.5 && resultado != 0.0F -> {
                return "IMC: $resultado, Classificação: Magreza"
            }
            resultado in 18.5..24.9 -> {
                return "IMC: $resultado, Classificação: Normal"
            }
            resultado in 25.0 .. 29.9 -> {
                return "IMC: $resultado, Classificação: Sobrepeso"
            }
            resultado in 30.0 .. 34.9 -> {
                return "IMC: $resultado, Classificação: Obesidade grau I"
            }
            resultado in 35.0 .. 39.9 -> {
                return "IMC: $resultado, Classificação: Obesidade grau II"
            }
            resultado >= 40.0 -> {
                return "IMC: $resultado, Classificação: Obesidade grau III"
            }
        }
        return "Valor incorreto, verifique e tente novamente!"
    }
}