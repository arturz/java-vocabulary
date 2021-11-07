package main;

import java.util.*;

public class History {
    private Set<String> usedWords = new TreeSet<>();
    private Set<String> usedLanguages = new TreeSet<>();

    public void addEntry(String word, Set<String> languages) {
        usedWords.add(word);
        languages.forEach(language -> usedLanguages.add(language));
    }

    public String getStatistics() {
        return "ilosc slow: " + this.usedWords.size() + ", jezyki: " + String.join(", ", this.usedLanguages);
    }
}
