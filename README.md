## 📘 Projeto: Gestor Acadêmico Android

Este é um aplicativo Android simples desenvolvido em Java que permite:

- Cadastrar **cursos** com nome, código e tempo de formação.
- Cadastrar **disciplinas** com nome e código.
- Relacionar disciplinas já cadastradas a cursos já cadastrados.
- Persistir os dados localmente com **SQLite**.

------

## 🧱 Funcionalidades

| Tela                               | Descrição                                            |
| ---------------------------------- | ---------------------------------------------------- |
| `MainActivity`                     | Tela inicial com botões para acesso às demais telas. |
| `CadastroCursoActivity`            | Tela para cadastrar cursos.                          |
| `CadastroDisciplinaActivity`       | Tela para cadastrar disciplinas.                     |
| `AdicionarDisciplinaCursoActivity` | Tela para relacionar disciplinas a cursos.           |

## 🧰 Tecnologias utilizadas

- **Java** (Android)
- **SQLite** para persistência local
- **Android SDK** (via Android Studio)
- `Spinner`, `EditText`, `Button`, `Toast`, etc.

------

## 🧪 Como rodar o projeto

1. **Clone o repositório:**

   ```
   bash
   
   
   CopiarEditar
   git clone https://github.com/seuusuario/gestor-academico-android.git
   ```

2. **Abra no Android Studio**

   Vá em **File > Open > Selecione a pasta do projeto**.

3. **Compile e execute no emulador ou dispositivo físico.**

------

## 🛠️ Banco de dados

- O banco é criado com `SQLiteOpenHelper`.
- Tabelas:
  - `Curso (id, nome, codigo, tempoFormacao)`
  - `Disciplina (id, nome, codigo)`
  - `CursoDisciplina (id, curso_id, disciplina_id)` 

------

## ⚠️ Observações

- O relacionamento entre curso e disciplina é validado para evitar duplicações.
- Caso não existam cursos ou disciplinas cadastrados, os spinners exibirão uma mensagem informativa.