package com.example.gerenciamentocursos;

import android.content.Intent;
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

        Button BtvoltarD = findViewById(R.id.btvoltarDiciplina);

        BtvoltarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pag_inicial(v);
            }
        });

        btnSalvarDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNomeDisciplina.getText().toString().trim();
                String codigo = edtCodigoDisciplina.getText().toString().trim();

                if (nome.isEmpty() || codigo.isEmpty()) {
                    Toast.makeText(CadastroDisciplina.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean resultado = verificarCodigoD(codigo);
                if (resultado) {
                    Toast.makeText(CadastroDisciplina.this, "Este código de disciplina já existe!", Toast.LENGTH_SHORT).show();
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
    public void pag_inicial(View v){
        Intent in = new Intent(this , MainActivity.class);
        startActivity(in);
    }

    public boolean verificarCodigoD(String codigo) {
        DisciplinaDAO dao = new DisciplinaDAO(this);
        boolean existe = dao.verificarCodigoDiciplina(codigo);
        return existe;
    }
}
