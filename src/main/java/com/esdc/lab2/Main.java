package com.esdc.lab2;


import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.parser.ParserChainBuilder;
import com.esdc.lab2.parser.TextParser;
import com.esdc.lab2.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = "src/main/resources/input.txt";

        try {

            String text = Files.readString(Path.of(filePath));

            TextParser parser = ParserChainBuilder.build();

            TextComponent parsedText = parser.parse(text);

            System.out.println("Оригинальный текст:\n");
            System.out.println(parsedText + "\n");

            TextService service = new TextService();
            TextComponent sortedText = service.sortParagraphsBySentenceCount(parsedText);

            System.out.println("Отсортированные абзацы по числу предложений:\n");
            System.out.println(sortedText);

        } catch (IOException e) {
            logger.error("Ошибка при чтении файла: " + filePath, e);
        } catch (Exception e) {
            logger.error("Непредвиденная ошибка", e);
        }
    }
}
