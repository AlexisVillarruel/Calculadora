package dev.alexisvillarruel.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var operacionActual: String = ""
    private var valor1 : Double = 0.0
    private var esNuevoOperando = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.textView)
        val buttonsNumeros = listOf(
            findViewById<Button>(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )
        for (button in buttonsNumeros) {
            button.setOnClickListener {
                manejarEntradaNumero(button.text.toString())
            }
        }

        findViewById<Button>(R.id.btnsuma).setOnClickListener { manejarOperacion("+") }
        findViewById<Button>(R.id.btnresta).setOnClickListener { manejarOperacion("-") }
        findViewById<Button>(R.id.btnmulti).setOnClickListener { manejarOperacion("*") }
        findViewById<Button>(R.id.btndiv).setOnClickListener { manejarOperacion("/") }

        findViewById<Button>(R.id.btnigual).setOnClickListener { calcularResultado() }

        }
    private fun manejarEntradaNumero(valor: String) {
        if (esNuevoOperando) {
            tvResult.text = ""
            esNuevoOperando = false
        }
        val textoActual = tvResult.text.toString()
        tvResult.text = textoActual + valor
    }
    private fun manejarOperacion(operacion: String) {
        valor1 = tvResult.text.toString().toDouble()
        operacionActual = operacion
        esNuevoOperando = true
    }

    private fun calcularResultado() {
        val valor2 = tvResult.text.toString().toDouble()
        val resultado = when (operacionActual) {
            "+" -> valor1 + valor2
            "-" -> valor1 - valor2
            "*" -> valor1 * valor2
            "/" -> {
                if (valor2 == 0.0) {
                    tvResult.text = "Error"
                    return
                } else {
                    valor1 / valor2
                }
            }
            else -> 0.0
        }
        tvResult.text = resultado.toString()
        esNuevoOperando = true
    }
    }
