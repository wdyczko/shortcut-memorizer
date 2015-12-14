package org.drvad3r;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Author: Wiktor
 * Creation date: 2015-12-09.
 */
public class Command {
    private StringProperty description;
    private StringProperty keystroke;

    public Command() {
        this(null, null);
    }

    public Command(String description, String command) {
        this.description = new SimpleStringProperty(description);
        this.keystroke = new SimpleStringProperty(command);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getKeystroke() {
        return keystroke.get();
    }

    public StringProperty keystrokeProperty() {
        return keystroke;
    }

    public void setKeystroke(String keystroke) {
        this.keystroke.set(keystroke);
    }
}
