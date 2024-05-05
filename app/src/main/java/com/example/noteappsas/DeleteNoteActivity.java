package com.example.noteappsas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteNoteActivity extends AppCompatActivity {

    private NoteManager noteManager;
    private ArrayAdapter<String> adapter;
    private Spinner spinnerSelectNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        noteManager = new NoteManager(this);

        spinnerSelectNote = findViewById(R.id.spinnerSelectNote);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, noteManager.getNotes());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectNote.setAdapter(adapter);
    }

    public void deleteSelectedNote(View view) {
        String selectedNote = (String) spinnerSelectNote.getSelectedItem();
        if (selectedNote != null) {
            noteManager.deleteNote(selectedNote);
            adapter.remove(selectedNote);
            adapter.notifyDataSetChanged();
            finish();
        }
    }
}
