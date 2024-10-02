package edu.upc.dsa.simplecalculatordsa;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentExpression = "";  // Almacena toda la expresión (acumulativa)
    private boolean isDegree = true;  // Para verificar si es en grados o radianes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.tv_num1);
        SwitchCompat switchDegrees = findViewById(R.id.switchdegrees3);

        // Listener para cambiar entre grados y radianes
        switchDegrees.setOnCheckedChangeListener((buttonView, isChecked) -> isDegree = isChecked);

        // Botones numéricos
        int[] numberButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn_dot};
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(v -> appendToExpression(((Button) v).getText().toString()));
        }

        // Botones de operaciones
        findViewById(R.id.btnmas).setOnClickListener(v -> appendToExpression("+"));
        findViewById(R.id.btnmenos).setOnClickListener(v -> appendToExpression("-"));
        findViewById(R.id.btnmutl).setOnClickListener(v -> appendToExpression("*"));
        findViewById(R.id.btndiv).setOnClickListener(v -> appendToExpression("/"));

        // Botón igual
        findViewById(R.id.btnigual).setOnClickListener(v -> calculateResult());

        // Botón para limpiar (Clear)
        findViewById(R.id.btnC).setOnClickListener(v -> clearAll());

        // Botones de funciones trigonométricas
        findViewById(R.id.btnsen).setOnClickListener(v -> performTrigOperation("sin"));
        findViewById(R.id.btncos).setOnClickListener(v -> performTrigOperation("cos"));
        findViewById(R.id.btntan).setOnClickListener(v -> performTrigOperation("tan"));
    }

    private void appendToExpression(String value) {
        currentExpression += value;
        display.setText(currentExpression);
    }

    private void calculateResult() {
        if (!currentExpression.isEmpty()) {
            try {
                double result = evaluateExpression(currentExpression);
                display.setText(String.valueOf(result));
                currentExpression = String.valueOf(result);  // El resultado se convierte en la nueva expresión para continuar operando
            } catch (Exception e) {
                display.setText("Error");
                currentExpression = "";
            }
        }
    }

    private double evaluateExpression(String expression) {
        // Aquí usaremos un método simple para evaluar la expresión acumulada
        // NOTA: Este es un evaluador muy básico y evalúa las operaciones en el orden en que aparecen.
        // No respeta el verdadero orden de operaciones (PEMDAS/BODMAS), tal como lo solicitaste.

        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");
        double result = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double nextNumber = Double.parseDouble(tokens[i + 1]);

            switch (operator) {
                case "+":
                    result += nextNumber;
                    break;
                case "-":
                    result -= nextNumber;
                    break;
                case "*":
                    result *= nextNumber;
                    break;
                case "/":
                    result /= nextNumber;
                    break;
            }
        }

        return result;
    }

    private void performTrigOperation(String operation) {
        if (!currentExpression.isEmpty()) {
            try {
                Double number = Double.parseDouble(currentExpression);
                double result = 0;

                switch (operation) {
                    case "sin":
                        result = isDegree ? Math.sin(Math.toRadians(number)) : Math.sin(number);
                        break;
                    case "cos":
                        result = isDegree ? Math.cos(Math.toRadians(number)) : Math.cos(number);
                        break;
                    case "tan":
                        result = isDegree ? Math.tan(Math.toRadians(number)) : Math.tan(number);
                        break;
                }

                display.setText(String.valueOf(result));
                currentExpression = String.valueOf(result);
            } catch (NumberFormatException e) {
                display.setText("Error");
            }
        }
    }

    private void clearAll() {
        currentExpression = "";
        display.setText("0.0");
    }
}

