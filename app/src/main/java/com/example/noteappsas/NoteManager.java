package com.example.noteappsas;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NoteManager {
    private static final String PREFS_NAME = "notes_app";
    private static final String NOTES_KEY = "notes";

    private SharedPreferences sharedPreferences;

    public NoteManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void addNote(String note) {
        Set<String> notes = new HashSet<>(getNotes());
        notes.add(note);
        saveNotes(notes);
    }

    public ArrayList<String> getNotes() {
        Set<String> notesSet = sharedPreferences.getStringSet(NOTES_KEY, new HashSet<>());
        return new ArrayList<>(notesSet);
    }

    public void deleteNote(String note) {
        Set<String> notes = new HashSet<>(getNotes());
        notes.remove(note);
        saveNotes(notes);
    }

    private void saveNotes(Set<String> notes) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(NOTES_KEY, notes);
        editor.apply();
    }
}
