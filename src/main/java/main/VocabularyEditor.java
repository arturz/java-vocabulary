package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class VocabularyEditor {
    private void fetchLanguagesFromJson() {

    }



    public VocabularyEditor() {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JodaModule());

        System.out.println("test");
    }
}
