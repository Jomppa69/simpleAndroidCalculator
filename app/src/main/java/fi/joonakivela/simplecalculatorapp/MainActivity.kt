package fi.joonakivela.simplecalculatorapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var editOper1: EditText
    private lateinit var editOper2: EditText
    private lateinit var textResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editOper1 = findViewById(R.id.editTextOperand1)
        editOper2 = findViewById(R.id.editTextOperand2)
        textResult = findViewById(R.id.textViewResult)
    }

    fun doCalc(view: View) {
        val oper1 = editOper1.text.toString().toDoubleOrNull()
        val oper2 = editOper2.text.toString().toDoubleOrNull()
        if (oper1 == null || oper2 == null) {
            textResult.text= getString(R.string.please_enter_number_values)
            return
        }
        var result: Double = 0.0
        when (view.id) {
            R.id.buttonPlus -> result = oper1 + oper2
            R.id.buttonSub -> result = oper1 - oper2
            R.id.buttonMul -> result = oper1 * oper2
            R.id.buttonDiv -> {
                if (oper2 == 0.0) {
                    textResult.text = getString(R.string.division_by_zero)
                    return
                }
                result = oper1 / oper2
            }
            R.id.buttonMod -> {
                if (oper2 == 0.0) {
                    textResult.text = getString(R.string.division_by_zero)
                    return
                }
                result = oper1 % oper2
            }
            R.id.buttonPow -> result = Math.pow(oper1, oper2)
        }
        textResult.text = String.format(Locale("Finnish", "FI"),"%.2f", result)
    }
}