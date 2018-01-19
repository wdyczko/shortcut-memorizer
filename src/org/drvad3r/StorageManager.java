package org.drvad3r;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Author: Wiktor
 * Creation date: 2015-12-09.
 */
public class StorageManager {
    public CommandList loadCommandDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(CommandList.class);
            Unmarshaller um = context.createUnmarshaller();
            return (CommandList) um.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CommandList load(String lessonName) {
        if (lessonName.contains(".xml"))
            return loadCommandDataFromFile(new File(System.getProperty("user.dir") + File.separator + "lesson" + File.separator + lessonName));
        else
            return loadCommandDataFromFile(new File(System.getProperty("user.dir") + File.separator + "lesson" + File.separator + lessonName + ".xml"));
    }

    public void saveCommandDataToFile(File file, CommandList list) {
        try {
            JAXBContext context = JAXBContext.newInstance(CommandList.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(list, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void save(String lessonName, CommandList list) {
        if (lessonName.contains(".xml"))
            saveCommandDataToFile(new File(System.getProperty("user.dir") + File.separator + "lesson" + File.separator + lessonName), list);
        else
            saveCommandDataToFile(new File(System.getProperty("user.dir") + File.separator + "lesson" + File.separator + lessonName + ".xml"), list);
    }

    public ObservableList<String> getLessonList() {
        final ObservableList<String> lessons = FXCollections.observableArrayList();
        try {
            Stream<Path> list = Files.list(new File(System.getProperty("user.dir") + File.separator + "lesson" + File.separator).toPath());
            list.forEach(path -> lessons.add(path.getFileName().toString().substring(0, path.getFileName().toString().length() - 4)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessons;
    }
}
