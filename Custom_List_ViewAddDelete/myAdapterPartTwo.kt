package com.example.sem71



import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class myAdapterPartTwo(
    private val context: Context,
    private val resource: Int,
    private val items: MutableList<Model2>
) : ArrayAdapter<Model2>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val image: ImageView = view.findViewById(R.id.DimageView)
        val title: TextView = view.findViewById(R.id.Dtext1)
        val desc: TextView = view.findViewById(R.id.Dtext2)
        val delete: Button = view.findViewById(R.id.Delbutton1)

        val model = items[position]

        image.setImageURI(model.imageUri)
        title.text = model.title
        desc.text = model.description

        delete.setOnClickListener {
            items.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }
}
