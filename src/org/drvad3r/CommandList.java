package org.drvad3r;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Author: Wiktor
 * Creation date: 2015-12-09.
 */

@XmlRootElement(name = "commands")
public class CommandList {
    private ObservableList<Command> commands;
    private String description;

    public CommandList() {
        this.commands = FXCollections.observableArrayList();
    }

    @XmlElement(name = "command")
    public ObservableList<Command> getCommands() {
        return commands;
    }

    public void setCommands(ObservableList<Command> commands) {
        this.commands = commands;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
