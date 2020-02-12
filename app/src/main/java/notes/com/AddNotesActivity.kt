package notes.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm
import java.lang.Exception

class AddNotesActivity : AppCompatActivity() {

    private lateinit var titleEdittext: EditText
    private lateinit var descriptionEdittext: EditText
    private lateinit var saveNotesButton: Button
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        titleEdittext = findViewById(R.id.title_edittext)
        descriptionEdittext = findViewById(R.id.description_edittext)
        saveNotesButton = findViewById(R.id.save_notes_button)
        realm = Realm.getDefaultInstance()

        saveNotesButton.setOnClickListener {
            addNotesToDB()
        }
    }

    private fun addNotesToDB() {
        try {
            realm.beginTransaction()
            val currentIdNumber: Number? = realm.where(Notes::class.java).max("id")
            val nextID: Int
            nextID = if(currentIdNumber == null) {
                1
            } else {
                currentIdNumber.toInt() + 1
            }

            val notes = Notes()
            notes.id = nextID
            notes.title = titleEdittext.text.toString()
            notes.description = descriptionEdittext.text.toString()

            realm.copyToRealmOrUpdate(notes)
            realm.commitTransaction()

            Toast.makeText(this, "Заметка успешно добавлена!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        } catch(e: Exception) {
            Toast.makeText(this, "Failed $e", Toast.LENGTH_SHORT).show()
        }
    }
}
