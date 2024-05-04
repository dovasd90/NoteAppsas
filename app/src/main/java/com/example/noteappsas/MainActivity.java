package com.example.noteappsas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NoteManager noteManager;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the NoteManager
        noteManager = new NoteManager(this);

        // Retrieve the notes from NoteManager
        ArrayList<String> notesList = noteManager.getNotes();

        // Set up the adapter and ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        ListView notesListView = findViewById(R.id.notesListView);
        notesListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_add_note) {
            startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            return true;
        } else if (id == R.id.menu_delete_note) {
            startActivity(new Intent(MainActivity.this, DeleteNoteActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh notes when returning from other activities
        adapter.clear();
        adapter.addAll(noteManager.getNotes());
        adapter.notifyDataSetChanged();
    }
}
