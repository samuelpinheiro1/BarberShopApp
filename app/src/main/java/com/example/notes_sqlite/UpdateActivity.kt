package com.example.notes_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes_sqlite.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: PaginaDataBaseHelper
    private var paginaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = PaginaDataBaseHelper(this)

        paginaId = intent.getIntExtra("note_id", -1)
        if (paginaId == -1) {
            finish()
            return
        }

        val pagina = db.getNoteByID(paginaId)
        binding.updateTitleEditText.setText(pagina.title)
        binding.updateContentEditText.setText(pagina.content)


        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updateNote = Pagina(paginaId, newTitle, newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Mudanças Salvas", Toast.LENGTH_SHORT).show()
        }


    }
}