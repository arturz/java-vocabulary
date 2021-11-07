package main;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class LanguageFinderTest {
    LanguageFinder languageFinder;

    @Before
    public void setUp() {
        Map<String, Set<String>> languagesWithWords = new TreeMap<>();
        languagesWithWords.put("angielski", new TreeSet<String>(Arrays.asList("cat", "dog", "but")));
        languagesWithWords.put("polski", new TreeSet<String>(Arrays.asList("słoń", "drzewo", "żuraw", "but")));
        this.languageFinder = new LanguageFinder(languagesWithWords);
    }

    @Test
    public void testGetLanguagesThatContainsWord() {
        Set <String> expectedLanguages = new TreeSet<String>(Arrays.asList("angielski"));
        Set <String> notExpectedLanguages = new TreeSet<String>(Arrays.asList("polski"));
        Set <String> outputLanguages = this.languageFinder.getLanguagesThatContainsWord("cat");
        assertEquals(expectedLanguages, outputLanguages);
        assertNotEquals(notExpectedLanguages, outputLanguages);
    }

    @Test
    public void testGetLanguagesThatContainsWord2() {
        Set <String> expectedLanguages = new TreeSet<String>(Arrays.asList("angielski", "polski"));
        Set <String> outputLanguages = this.languageFinder.getLanguagesThatContainsWord("but");
        assertEquals(expectedLanguages, outputLanguages);
    }

    @Test
    public void testGetLanguagesThatContainsWord3() {
        Set <String> expectedLanguages = new TreeSet<String>();
        Set <String> outputLanguages = this.languageFinder.getLanguagesThatContainsWord("żyrafa");
        assertEquals(expectedLanguages, outputLanguages);
    }
}
