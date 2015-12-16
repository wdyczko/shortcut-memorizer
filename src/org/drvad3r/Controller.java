package org.drvad3r;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by wdyczko on 12/9/2015.
 */
public class Controller {
    public static final String CORRECT_LABEL_STYLE = "-fx-text-fill: linear-gradient(green, #004500); -fx-font-size: 24px;";
    public static final String WRONG_LABEL_STYLE = "-fx-text-fill: linear-gradient(#ff0000, #5d0000); -fx-font-size: 24px;";
    @FXML
    private Label progressLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label statusLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ComboBox lessonComboBox;
    @FXML
    private Label inputLabel;

    private Command current;
    private CommandList commandList;
    private ArrayList<Integer> passed = new ArrayList<>();
    private int currentIndex = -1;
    private StorageManager storageManager;

    public void initialize() {
        storageManager = new StorageManager();
        inputLabel.setFocusTraversable(true);
        inputLabel.requestFocus();
        lessonComboBox.setItems(storageManager.getLessonList());
        lessonComboBox.setFocusTraversable(false);
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        String prefix = determinePrefix(keyEvent);
        if (keyEvent.getCode() == KeyCode.CONTROL || keyEvent.getCode() == KeyCode.ALT || keyEvent.getCode() == KeyCode.SHIFT) {
            keyEvent.consume();
            return;
        } else {
            String keyStroke = getKeyStroke(keyEvent, prefix);
            inputLabel.setText(keyStroke);
            checkPassCondition(keyStroke);
        }
    }

    private void checkPassCondition(String keyStroke) {
        if (current.getKeystroke().trim().equals(keyStroke.trim())) {
            statusLabel.setStyle(CORRECT_LABEL_STYLE);
            statusLabel.setText("Correct! " + current.getKeystroke());
            inputLabel.setText("");
            randomizeCommand();
//            recoverEmptyStatusAfterTime(2);
        } else {
            statusLabel.setStyle(WRONG_LABEL_STYLE);
            statusLabel.setText("Wrong!!! Should be: " + current.getKeystroke());
        }
    }

    private void recoverEmptyStatusAfterTime(int time) {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(time),
                actionEvent -> statusLabel.setText("")
        ));
        timeline.play();
    }

    private String getKeyStroke(KeyEvent keyEvent, String prefix) {
        if (prefix.isEmpty()) {
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
        } else if (keyEvent.isControlDown() && keyEvent.isAltDown() && !keyEvent.isShiftDown()) {
            prefix = "Ctrl + Alt";
        } else if (keyEvent.isControlDown() && !keyEvent.isAltDown() && keyEvent.isShiftDown()) {
            prefix = "Ctrl + Shift";
        } else if (!keyEvent.isControlDown() && keyEvent.isAltDown() && keyEvent.isShiftDown()) {
            prefix = "Alt + Shift";
        } else if (keyEvent.isControlDown() && !keyEvent.isAltDown() && !keyEvent.isShiftDown()) {
            prefix = "Ctrl";
        } else if (!keyEvent.isControlDown() && keyEvent.isAltDown() && !keyEvent.isShiftDown()) {
            prefix = "Alt";
        } else if (!keyEvent.isControlDown() && !keyEvent.isAltDown() && keyEvent.isShiftDown()) {
            prefix = "Shift";
        }
        return prefix;
    }

    private void randomizeCommand() {
        Random random = new Random();
        do {
            currentIndex = random.nextInt(commandList.getCommands().size());
        } while (passed.contains(currentIndex));
        progressBar.setProgress( (double) passed.size() / (double) commandList.getCommands().size() );
        progressLabel.setText(String.format("%d/%d", passed.size(), commandList.getCommands().size()));
        passed.add(currentIndex);
        if (passed.size() == commandList.getCommands().size()) {
            passed.clear();
        }
        current = commandList.getCommands().get(currentIndex);
        descriptionLabel.setText(current.getDescription());
    }

    @FXML
    private void onLessonChoose(ActionEvent actionEvent) {
        inputLabel.setText("");
        statusLabel.setText("");
        commandList = storageManager.load(lessonComboBox.getSelectionModel().getSelectedItem().toString());
        passed.clear();
        currentIndex = -1;
        randomizeCommand();
        inputLabel.requestFocus();
        progressBar.setVisible(true);
    }
}
