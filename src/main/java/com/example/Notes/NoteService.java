package com.example.Notes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class NoteService implements NoteCRUDService{
    private final NoteRepository repository;

    @Override
    public List<Note> listAll() {
       return repository.findAll();
    }

    @Override
    public Note add(Note note) {
        repository.save(note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        Optional<Note> noteFound = repository.findById(id);
        try {
            if (noteFound.isPresent()) {
                repository.deleteById(id);
            }else throw new NoteNotFoundException("Note with id " + id + " not found");
        } catch (NoteNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Note note){
        Optional<Note> noteFound = repository.findById(note.getId());
        try {
            if (noteFound.isPresent()){
            repository.save(note);
        }else throw new NoteNotFoundException("Note not found");
        } catch (NoteNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public Note getById(long id) {
        Optional<Note> noteFound = repository.findById(id);
        try {
            if (noteFound.isEmpty()) {
                throw new NoteNotFoundException("Note not found");
            }
        } catch (NoteNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return repository.getReferenceById(id);
    }
}