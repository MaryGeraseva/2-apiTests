package common;

import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ReportParser {

    public static void main(String[] args) {

    }

    //get all files paths from dir
    private static ArrayList<Path> getFilesPaths(String dirPath) {
        ArrayList<Path> pathList = new ArrayList<>();
        try (Stream<Path> filePathStream = Files.walk(Paths.get(dirPath))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    pathList.add(filePath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathList;
    }

    public static Path getNewFilePath(ExtensionContext context) {
        Path result = null;
        ArrayList<Path> pathList = getFilesPaths("build/reports/logsByTestMethod");
        for (Path path :pathList) {
            if (path.toString().contains(context.getTestMethod().get().getName())) {
                result = path;
            }
        }
        return result;
    }

    public static void separateLogFiles() {
        //get all files paths from dir
        ArrayList<Path> pathList = getFilesPaths("build/reports/logsByThread");

        //red-create-write new logs
        for(Path path : pathList) {
            try (BufferedReader reader = Files.newBufferedReader(path)){
                String line;
                String filePath = null;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    if (line.contains("[TestListener]") & line.contains("started")) {
                        int start = line.indexOf("]") + 1;
                        int end = line.indexOf("started");
                        String fileName = line.substring(start, end).trim();
                        filePath = String.format("build/reports/logsByTestMethod/%s.log", fileName);
                        new File(filePath);
                        builder.append(line).append("\n");
                    }
                    else if (line.contains("finished") |
                             line.contains("disabled") |
                             line.contains("aborted") |
                             line.contains("failed")) {
                        builder.append(line);
                        FileWriter writer = new FileWriter(filePath);
                        writer.write(builder.toString());
                        writer.flush();
                        writer.close();
                        builder.setLength(0);
                    }
                    else {
                        builder.append(line).append("\n");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
