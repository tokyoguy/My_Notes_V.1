package notes.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.delete

class MainActivity : AppCompatActivity() {

    private lateinit var addNotes: FloatingActionButton
    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var notesList: ArrayList<Notes>
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNotes = findViewById(R.id.add_notes)
        notesRecyclerView = findViewById(R.id.notes_recycler_view)
        realm = Realm.getDefaultInstance()

        addNotes.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
            finish()
        }

        notesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        getAllNotes()
    }

    private fun getAllNotes() {
        notesList = ArrayList()
        var results: RealmResults<Notes> = realm.where<Notes>(Notes::class.java).findAll()
        notesRecyclerView.adapter = NotesAdapter(this, results)
        notesRecyclerView.adapter!!.notifyDataSetChanged()
    }
}
