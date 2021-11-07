package main;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.databind.SerializationFeature;

public class VocabularyEditor {
    ObjectMapper jsonMapper;
    private Map<String, Set<String>> languagesWithWords = new TreeMap<>();

    private void deserializeLanguages() throws IOException {
        Set<String> languages = this.jsonMapper.readValue(ResourceHelper.getResourceAsStream("languages.json"), new TypeReference<TreeSet<String>>(){});

        Iterator<String> languagesIterator = languages.iterator();
        while(languagesIterator.hasNext()) {
            String language = languagesIterator.next();
            Set<String> words = this.jsonMapper.readValue(ResourceHelper.getResourceAsStream(language + ".json"), new TypeReference<TreeSet<String>>(){});
            this.languagesWithWords.put(language, words);
        }

        System.out.print("Dostepne jezyki: ");
        System.out.println(languages.stream().map(language -> {
            return language + " (" + this.languagesWithWords.get(language).size() + " slow)";
        }).collect(Collectors.joining(", ")));
    }

    public void createLanguage(String language, Set<String> words) throws IOException {
        this.languagesWithWords.put(language, words);

        String languagesJsonPath = ResourceHelper.getResourceAsPath("languages.json");
        File languagesJsonFile = new File(languagesJsonPath);

        // override languages.json
        languagesJsonFile.delete();
        this.jsonMapper.writeValue(languagesJsonFile, this.languagesWithWords.keySet());

        String newLanguagePath = languagesJsonFile.getParentFile().getAbsolutePath() + "/" + ResourceHelper.encodeFilename(language) + ".json";
        File newLanguageFile = new File(newLanguagePath);

        // override [language].json
        newLanguageFile.delete();
        this.jsonMapper.writeValue(newLanguageFile, this.languagesWithWords.get(language));
    }

    public Set<String> getLanguagesThatContainsWord(String word) {
        return (new LanguageFinder(this.languagesWithWords)).getLanguagesThatContainsWord(word);
    }

    public VocabularyEditor() throws IOException {
        this.jsonMapper = new ObjectMapper();
        this.jsonMapper.registerModule(new JodaModule()); // handle dates
        this.jsonMapper.enable(SerializationFeature.INDENT_OUTPUT); // pretty output
        this.deserializeLanguages();
    }
}
