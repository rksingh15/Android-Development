package com.example.sem71

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
// Model2,myAdapterPart2,listviewdaytwo.xml,activity_list_view_part2.xml, (main)ListView_Part2.kt
class ListView_Part2 : AppCompatActivity() {
    private val PICK_IMAGE = 100
    private var selectedImageUri: Uri? = null

    private lateinit var adapter: myAdapterPartTwo
    private lateinit var list: MutableList<Model2>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view_part2)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val titleInput = findViewById<EditText>(R.id.text1)
        val descInput = findViewById<EditText>(R.id.text2)
        val addButton = findViewById<Button>(R.id.button1)
        val listView = findViewById<ListView>(R.id.listView)

        list = mutableListOf()
        adapter = myAdapterPartTwo(this, R.layout.listviewdaytwo, list)
        listView.adapter = adapter

        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        addButton.setOnClickListener {
            val title = titleInput.text.toString()
            val desc = descInput.text.toString()
            val imageUri = selectedImageUri // ✅ local immutable variable

            if (title.isNotEmpty() && desc.isNotEmpty() && imageUri != null) {
                val model = Model2(imageUri, title, desc)
                list.add(model)
                adapter.notifyDataSetChanged()

                titleInput.text.clear()
                descInput.text.clear()
                imageView.setImageResource(R.drawable.ic_launcher_foreground)
                selectedImageUri = null
            } else {
                Toast.makeText(this, "Fill title, desc and select image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageURI(selectedImageUri)
        }
    }
}
