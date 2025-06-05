package com.example.gerenciamentocursos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class CadastroDisciplina extends AppCompatActivity {

    private EditText edtNomeDisciplina, edtCodigoDisciplina;
    private Button btnSalvarDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cadastro_disciplina);

        edtNomeDisciplina = findViewById(R.id.edtNomeDisciplina);
        edtCodigoDisciplina = findViewById(R.id.edtCodigoDisciplina);
        btnSalvarDisciplina = findViewById(R.id.btnSalvarDisciplina);

        btnSalvarDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNomeDisciplina.getText().toString().trim();
                String codigo = edtCodigoDisciplina.getText().toString().trim();

                if (nome.isEmpty() || codigo.isEmpty()) {
                    Toast.makeText(CadastroDisciplina.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                DisciplinaDAO dao = new DisciplinaDAO(CadastroDisciplina.this);
                long id = dao.inserirDisciplina(nome, codigo);

                if (id != -1) {
                    Toast.makeText(CadastroDisciplina.this, "Disciplina cadastrada!", Toast.LENGTH_SHORT).show();
                    edtNomeDisciplina.setText("");
                    edtCodigoDisciplina.setText("");
                } else {
                    Toast.makeText(CadastroDisciplina.this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
