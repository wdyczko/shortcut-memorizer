package org.drvad3r;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by wdyczko on 12/9/2015.
 */
public class Controller {
    @FXML
    private Label inputLabel;
    @FXML
    private TextField inputTextField;

    public void initialize()
    {
        inputLabel.setFocusTraversable(true);
        inputLabel.requestFocus();
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        String prefix = determinePrefix(keyEvent);
        if (keyEvent.getCode() == KeyCode.CONTROL ||keyEvent.getCode() == KeyCode.ALT || keyEvent.getCode() == KeyCode.SHIFT) {
            keyEvent.consume();
            return;
        }
        else {
            inputLabel.setText(getKeyStroke(keyEvent, prefix));
        }
    }

    private String getKeyStroke(KeyEvent keyEvent, String prefix) {
        if(prefix.isEmpty())
        {
            return getKeyRepresentation(keyEvent);
        } else {
            return prefix + " + " + getKeyRepresentation(keyEvent);
        }
    }

    private String getKeyRepresentation(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case TAB:
                return "Tab";
            case F1:
                return "F1";
            case F2:
                return "F2";
            case F3:
                return "F3";
            case F4:
                return "F4";
            case F5:
                return "F5";
            case F6:
                return "F6";
            case F7:
                return "F7";
            case F8:
                return "F8";
            case F9:
                return "F9";
            case F10:
                return "F10";
            case F11:
                return "F11";
            case F12:
                return "F12";
            case ENTER:
                return "Enter";
            case BACK_SPACE:
                return "Backspace";
            case SPACE:
                return "Space";
            case ESCAPE:
                return "Escape";
            case NUMPAD0:
                return "NumPad0";
            case NUMPAD1:
                return "NumPad1";
            case NUMPAD2:
                return "NumPad2";
            case NUMPAD3:
                return "NumPad3";
            case NUMPAD4:
                return "NumPad4";
            case NUMPAD5:
                return "NumPad5";
            case NUMPAD6:
                return "NumPad6";
            case NUMPAD7:
                return "NumPad7";
            case NUMPAD8:
                return "NumPad8";
            case NUMPAD9:
                return "NumPad9";
            case HOME:
                return "Home";
            case DELETE:
                return "Delete";
            case END:
                return "End";
            case INSERT:
                return "Insert";
            case PAGE_UP:
                return "PageUp";
            case PAGE_DOWN:
                return "PageDown";
            case LEFT:
                return "Left";
            case RIGHT:
                return "Right";
            case UP:
                return "Up";
            case DOWN:
                return "Down";
            default:
                return keyEvent.getText().toUpperCase();
        }
    }

    private String determinePrefix(KeyEvent keyEvent) {
        String prefix = "";
        if (keyEvent.isControlDown() && keyEvent.isAltDown() && keyEvent.isShiftDown()) {
            prefix = "Ctrl + Alt + Shift";
        } else if(keyEvent.isControlDown() && keyEvent.isAltDown() && !keyEvent.isShiftDown())
        {
            prefix = "Ctrl + Alt";
        } else if(keyEvent.isControlDown() && !keyEvent.isAltDown() && keyEvent.isShiftDown())
        {
            prefix = "Ctrl + Shift";
        } else if (!keyEvent.isControlDown() && keyEvent.isAltDown() && keyEvent.isShiftDown())
        {
            prefix = "Alt + Shift";
        } else if (keyEvent.isControlDown() && !keyEvent.isAltDown() && !keyEvent.isShiftDown())
        {
            prefix = "Ctrl";
        } else if (!keyEvent.isControlDown() && keyEvent.isAltDown() && !keyEvent.isShiftDown())
        {
            prefix = "Alt";
        } else if (!keyEvent.isControlDown() && !keyEvent.isAltDown() && keyEvent.isShiftDown())
        {
            prefix = "Shift";
        }
        return prefix;
    }

}
