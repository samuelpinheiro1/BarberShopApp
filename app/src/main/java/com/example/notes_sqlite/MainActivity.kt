package com.example.notes_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes_sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: PaginaDataBaseHelper
    private lateinit var paginaAdapter: PaginaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = PaginaDataBaseHelper(this)
        paginaAdapter = PaginaAdapter(db.pegarTodasPaginas(), this)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = paginaAdapter


        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddNotasAtv::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        paginaAdapter.refreshData(db.pegarTodasPaginas())
    }
}