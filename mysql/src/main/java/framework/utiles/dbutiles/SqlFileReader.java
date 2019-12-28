package framework.utiles.dbutiles;

import exceptions.NoSqlScriptFileException;
import framework.utiles.Printer;
import framework.utiles.PropertiesReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SqlFileReader {
    private String name;

    public SqlFileReader(String name) {
        this.name = name;
    }

    @Override
    public String toString()   {
        try (BufferedReader bufferedReader
                     = new BufferedReader(new FileReader(PropertiesReader.getValue(name)))) {
            return bufferedReader
                    .lines().collect(Collectors.joining());
        } catch (Exception e) {
            final NoSqlScriptFileException noSqlScriptFileException = new NoSqlScriptFileException(PropertiesReader.getValue(name) + e);
            Printer.print(Arrays.toString(noSqlScriptFileException.getStackTrace()));
            throw noSqlScriptFileException;

        }
    }
}
