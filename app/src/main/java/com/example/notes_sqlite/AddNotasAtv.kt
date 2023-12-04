package com.example.notes_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes_sqlite.databinding.ActivityAddNotasAtvBinding

class AddNotasAtv : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotasAtvBinding
    private lateinit var db: PaginaDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotasAtvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = PaginaDataBaseHelper(this)

        binding.SaveButton.setOnClickListener{
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val pagina = Pagina(0, title, content)
            db.inserirPagina(pagina)
            finish()
            Toast.makeText(this, "Página Salva", Toast.LENGTH_SHORT).show()
        }
    }
}