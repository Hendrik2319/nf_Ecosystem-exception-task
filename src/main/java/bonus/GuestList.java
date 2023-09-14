package bonus;

import lombok.Getter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class GuestList {
    private static final String GUESTS_TXT = "guests.txt";

    private final List<String> guests;

    public GuestList() {
        this(false);
    }

    public GuestList(boolean propagateExceptions) {
        guests = new ArrayList<>();
        readFromFile(propagateExceptions);
    }

    public void addGuest(String guest) {
        guests.add(guest);
        writetoFile();
    }

    public void setGuests(List<String> list) {
        guests.clear();
        guests.addAll(list);
        writetoFile();
    }

    private void readFromFile(boolean propagateExceptions) {
        Path path = Path.of(GUESTS_TXT);
        try (Stream<String> stream = Files.lines(path)) {
            guests.clear();
            guests.addAll(stream.toList());
        } catch (NoSuchFileException e) {
            if (propagateExceptions)
                throw new RuntimeException("%s while reading \"%s\"".formatted(e.getClass().getSimpleName(), path.toAbsolutePath()), e);
        } catch (IOException e) {
            System.err.printf("%s while reading \"%s\": %s", e.getClass().getSimpleName(), path.toAbsolutePath(), e.getMessage());
            if (propagateExceptions)
                throw new RuntimeException("%s while reading \"%s\"".formatted(e.getClass().getSimpleName(), path.toAbsolutePath()), e);
        }
    }

    private void writetoFile() {
        try {
            Files.write(Path.of(GUESTS_TXT), guests, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.printf("%s while writing \"%s\": %s%n", e.getClass().getSimpleName(), GUESTS_TXT, e.getMessage());
        }
    }
}
