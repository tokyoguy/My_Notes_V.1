package notes.com

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import kotlinx.android.synthetic.main.notes_rv_layot.view.*

class NotesAdapter(private val context: Context, private val notesList: RealmResults<Notes>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.notes_rv_layot, p0, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.title_TV.text = notesList[position]!!.title
        holder.itemView.description_TV.text = notesList[position]!!.description
    }

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val title = itemView.findViewById<TextView>(R.id.title_TV)
        val description = itemView.findViewById<TextView>(R.id.description_TV)
    }
}