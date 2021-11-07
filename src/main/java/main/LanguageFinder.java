package main;

import java.util.*;
import java.util.stream.Collectors;

public class LanguageFinder {
    Map<String, Set<String>> languagesWithWords;

    public LanguageFinder(Map<String, Set<String>> languagesWithWords) {
        this.languagesWithWords = languagesWithWords;
    }

    public Set<String> getLanguagesThatContainsWord(String word) {
        return this.languagesWithWords
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(word))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
