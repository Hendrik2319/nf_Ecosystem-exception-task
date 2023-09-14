package bonus;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GuestListTest {

    private static final String GUESTS_TXT = "guests.txt";

    /*
            •	Test 1:
            ⁃	Schreibe einen Test shouldBeEmptyInitially, der
            2	eine Instanz von der Klasse GuestList erzeugt
            3	darauf die Methode setGuests mit einer leeren Liste aufruft
            4	dann getGuests aufruft (Ergebnis soll eine Liste von Strings sein)
            5	und prüft, dass dieses Ergebnis eine leere Liste ist
        */
    @Test
    public void shouldBeEmptyInitially() {
        // Given
        GuestList guestList = new GuestList();
        guestList.setGuests(List.of());

        // When
        List<String> actual = guestList.getGuests();

        // Then
        assertNotNull(actual);

        List<String> expected = List.of();
        assertEquals(expected, actual);
    }

/*
	•	Test 2:
	⁃	Schreibe einen Test shouldReadSameGuestsAsWrittenBefore, der
	2	eine Instanz von der Klasse GuestList erzeugt
	3	darauf die Methode setGuests mit Karl und Ute aufruft
	4	dann getGuests aufruft (Ergebnis soll eine Liste von Strings sein)
	5	und prüft, dass dieses Ergebnis Karl und Ute enthält
*/
    @Test
    public void shouldReadSameGuestsAsWrittenBefore() {
        // Given
        GuestList guestList = new GuestList();
        guestList.setGuests(List.of("Karl","Ute"));

        // When
        List<String> actual = guestList.getGuests();

        // Then
        assertNotNull(actual);

        List<String> expected = List.of("Karl","Ute");
        assertEquals(expected, actual);
    }

/*
	•	Test 3:
	⁃	Schreibe einen Test shouldWriteToFileSystem, der
	2	eine Instanz von der Klasse GuestList erzeugt
	3	darauf die Methode setGuests mit Theodor und Anette aufruft
	4	und prüft, dass die Datei guests.txt angelegt wurde und die Zeilen "Theodor" und "Anette" enthält
	5	Tipp: Dateipfad definieren mit Path.of("guests.txt")
	6	Tipp: Lesen und Schreiben mit der java.nio.file.Files-Klasse
*/
    @Test
    public void shouldWriteToFileSystem() {
        // Given
        GuestList guestList = new GuestList();
        guestList.setGuests(List.of("Theodor","Anette"));

        // When

        // Then
        File file = new File(GUESTS_TXT);
        assertTrue(file.isFile());

        List<String> actual = null;
        try ( Stream<String> stream = Files.lines(file.toPath()); ) {
            actual = stream.toList();
        } catch (IOException e) {
            fail("Test[ shouldWriteToFileSystem ]: IOException while reading \"%s\"".formatted(file.getAbsolutePath()));
        }

        List<String> expected = List.of("Theodor","Anette");
        assertEquals(expected, actual);
    }

/*
•	Test 4:
	⁃	Schreibe einen Test shouldReadFromFileSystem, der
	2	In die Datei guests.txt Stephan und Max schreibt (als Zeilen)
	3	eine Instanz von der Klasse "GuestList" erzeugt
	4	dann getGuests aufruft
	5	und prüft, dass "Stephan" und "Max" gelesen wurden
	6	Tipp: Dateipfad definieren mit Path.of("guests.txt")
	7	Tipp: Lesen und Schreiben mit der java.nio.file.Files-Klasse
*/
    @Test
    public void shouldReadFromFileSystem() {
        // Given
        try {
            Files.write(Path.of(GUESTS_TXT), List.of("Stephan","Max"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.printf("Test[ shouldReadFromFileSystem ]: IOException while writing \"%s\": %s%n", GUESTS_TXT, e.getMessage());
        }
        GuestList guestList = new GuestList();

        // When
        List<String> actual = guestList.getGuests();

        // Then
        assertNotNull(actual);

        List<String> expected = List.of("Stephan","Max");
        assertEquals(expected, actual);

    }

    @Test
    public void test() {

    }

}