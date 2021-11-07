package main;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

public class Main {
    private VocabularyEditor editor;
    private Scanner scan;
    private History history;

    private void findLanguagesOption() {
        System.out.println("Wpisz słowo: ");
        String word = scan.nextLine();
        Set<String> languages = this.editor.getLanguagesThatContainsWord(word);
        this.history.addEntry(word, languages);
        if (languages.size() == 0) {
            System.out.println("Nie ma takiego slowa w bazie");
        } else {
            System.out.print("Slowo wystepuje w jezykach: ");
            System.out.println(String.join(", ", languages));
        }
    }

    private void createLanguageOption() throws IOException {
        System.out.println("Wpisz nazwe jezyka: ");
        String language = scan.nextLine();
        System.out.println("Podaj slowa rodzielone przecinkiem (bez spacji!):");
        Set<String> words = new HashSet<String>(Arrays.asList(scan.nextLine().split(",")));
        this.editor.createLanguage(language, words);
        System.out.println("Pomyslnie dodano jezyk!");
    }

    private void endProgrammeOption() throws IOException {
        System.out.println(this.history.getStatistics());
    }

    public Main() throws IOException {
        editor = new VocabularyEditor();
        scan = new Scanner(System.in);
        history = new History();

        while(true) {
            System.out.println("Chcesz dowiedzieć się z jakiego języka pochodzi dane słowo czy dodać własny język?");
            System.out.println("1 -> znajdź z jakiego języka pochodzi słowo");
            System.out.println("2 -> dodaj własny język");
            System.out.println("3 -> zakończ program");

            switch(scan.nextLine()) {
                case "1":
                    System.out.println("Wybrałeś opcję 1");
                    this.findLanguagesOption();
                    break;
                case "2":
                    System.out.println("Wybrałeś opcję 2");
                    this.createLanguageOption();
                    break;
                case "3":
                    System.out.println("Kończę działanie programu");
                    this.endProgrammeOption();
                    return;
                default:
                    System.out.println("Wybierz opcję 1, 2 lub 3");
                    continue;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}
