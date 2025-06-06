package com.example.gerenciamentocursos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CadastroCurso extends AppCompatActivity {

    private EditText edtNomeCurso, edtCodigoCurso, edtTempoFormacao;
    private Button btnSalvarCurso;
    private SQLiteDatabase db;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cadastro_curso);

        edtNomeCurso = findViewById(R.id.edtNomeCurso);
        edtCodigoCurso = findViewById(R.id.edtCodigoCurso);
        edtTempoFormacao = findViewById(R.id.edtTempoFormacao);
        btnSalvarCurso = findViewById(R.id.btnSalvarCurso);

        Button BtvoltarC = findViewById(R.id.btvoltarCurso);

        BtvoltarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pag_inicial(v);
            }
        });

        btnSalvarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNomeCurso.getText().toString().trim();
                String codigo = edtCodigoCurso.getText().toString().trim();
                String tempoFormacaoStr = edtTempoFormacao.getText().toString().trim();

                if (nome.isEmpty() || codigo.isEmpty() || tempoFormacaoStr.isEmpty()) {
                    Toast.makeText(CadastroCurso.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean resultado = verificarCodigoC(codigo);
                if (resultado) {
                    Toast.makeText(CadastroCurso.this, "Este código de curso já existe!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int tempoFormacao;
                try {
                    tempoFormacao = Integer.parseInt(tempoFormacaoStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(CadastroCurso.this, "Tempo de formação inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                CursoDAO dao = new CursoDAO(CadastroCurso.this);
                long id = dao.inserirCurso(nome, codigo, tempoFormacao);

                if (id != -1) {
                    Toast.makeText(CadastroCurso.this, "Curso cadastrado!", Toast.LENGTH_SHORT).show();
                    edtNomeCurso.setText("");
                    edtCodigoCurso.setText("");
                    edtTempoFormacao.setText("");
                } else {
                    Toast.makeText(CadastroCurso.this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void pag_inicial(View v){
        Intent in = new Intent(this , MainActivity.class);
        startActivity(in);
    }

    public boolean verificarCodigoC(String codigo) {
        CursoDAO dao = new CursoDAO(this);
        boolean existe = dao.verificarCodigoCurso(codigo);
        return existe;
    }

}
