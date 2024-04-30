package com.example.Notes;

public class NoteNotFoundException extends Exception{
    public NoteNotFoundException(String errorMassage){
        super(errorMassage);
    }
}
