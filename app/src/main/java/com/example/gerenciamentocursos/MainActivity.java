package com.example.gerenciamentocursos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastroCurso, btnCadastroDisciplina, btnRelacionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pag_inicial);

        btnCadastroCurso = findViewById(R.id.btnCadastroCurso);
        btnCadastroDisciplina = findViewById(R.id.btnCadastroDisciplina);
        btnRelacionar = findViewById(R.id.btnRelacionar);

        btnCadastroCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastroCurso.class));
            }
        });

        btnCadastroDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastroDisciplina.class));
            }
        });

        btnRelacionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdicionarDisciplinaCurso.class));
            }
        });

    }
}