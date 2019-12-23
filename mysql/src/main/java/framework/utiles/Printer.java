package framework.utiles;

import dnl.utils.text.table.TextTable;
import exceptions.NoResultFileException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class Printer {
    private static final String NAME_OUTPUT_FILE = "resultOfQuery.txt";

    public static <T> void print(List<T> modelsList, String[] header) {
        try (PrintStream printStream =
                     new PrintStream(new FileOutputStream(NAME_OUTPUT_FILE, true))) {
            Object[][] modelsAsRow = new Object[modelsList.size()][];
            for (int i = 0; i < modelsList.size(); i++) {
                modelsAsRow[i] = modelsList.get(i).toString().split("\\|");
            }
            new TextTable(header, modelsAsRow).printTable(printStream, 0);
        } catch (FileNotFoundException e) {
            throw new NoResultFileException("StackTrace:" + e);
        }
    }

    public static void print(String text) {
        try (PrintStream printStream = new PrintStream(new FileOutputStream(NAME_OUTPUT_FILE, true))) {
            printStream.println(text);
        } catch (FileNotFoundException e) {
            throw new NoResultFileException("StackTrace:" + e);
        }
    }
}
