package main;

import java.util.ArrayList;
import java.util.List;

public class Phrase {

    private String phrase;
    private List<Character> characters;

    public Phrase(String phrase) {
        this.phrase = phrase;
        this.characters = splitPhrase(phrase);
    }

    public List<Character> splitPhrase(String phrase) {
        List<Character> phraseCharacters = new ArrayList<>();
        for (char c : phrase.toCharArray()) {
            phraseCharacters.add(c);
        }
        return phraseCharacters;
    }

    public String getPhrase() {
        return phrase;
    }

    public List<Character> getCharacters() {
        return characters;
    }

}
