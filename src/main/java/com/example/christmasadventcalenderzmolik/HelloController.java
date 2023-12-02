package com.example.christmasadventcalenderzmolik;

import com.google.gson.Gson;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class HelloController {

    @FXML
    public Ellipse light1;
    @FXML
    public Ellipse light2;
    @FXML
    public Ellipse light3;
    @FXML
    public Ellipse light4;
    @FXML
    public Label presentNum;
    @FXML
    public Label button;
    @FXML
    public Label date;
    @FXML
    public Label presentName;
    @FXML
    public Label presentDesc;
    @FXML
    private Rectangle presentNumBg;
    private Color color1 = Color.rgb(186,186,186);
    private Color color2 = Color.rgb(255,81,81);

    private Color color3 = Color.RED;

    private Color color4 = Color.GREEN;
    private Color currentColor = color1;
    private Color currentColorLights = color4;
    private long lastUpdate = 0;
    private int currPresents = 0;
    private boolean isDecember = false;
    private int lastDayOn = 0;
    private boolean clickValid = false;
    private int maxPresents = 24;
    private int totalPresents = 0;

    @FXML
    protected void initialize() throws IOException {
        checkMonth();
        if (isDecember) {
            readData();
            daysFromLastOn();
        }
        presentNum.setText(currPresents + "");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        date.setText(dtf.format(now));
        writeData();
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (lastUpdate == 0) {
                lastUpdate = now;
            }

            double elapsedSeconds = (now - lastUpdate) / 1e9;

            if (elapsedSeconds > 0.5 && currPresents > 0) {
                toggleColor();
                toggleLights();
                lastUpdate = now;
            } else if (elapsedSeconds > 0.5) {
                toggleLights();
                lastUpdate = now;
            }
        }
    };

    private void toggleColor() {
        if (currentColor.equals(color1)) {
            currentColor = color2;
        } else {
            currentColor = color1;
        }
        presentNumBg.setFill(currentColor);
    }

    private void toggleLights() {
        if (currentColorLights.equals(color3)) {
            currentColorLights = color4;
            light1.setFill(color4);
            light2.setFill(color3);
            light3.setFill(color4);
            light4.setFill(color3);
        } else {
            currentColorLights = color3;
            light1.setFill(color3);
            light2.setFill(color4);
            light3.setFill(color3);
            light4.setFill(color4);
        }
    }

    private void updatePresentNum(int daysFromLastOn) {
        if (totalPresents + daysFromLastOn >= maxPresents) {
            currPresents += maxPresents-totalPresents;
            totalPresents = currPresents;
        } else {
            currPresents = currPresents + daysFromLastOn;
            totalPresents += daysFromLastOn;
            presentNum.setText(String.valueOf(currPresents));
        }
    }

    private void daysFromLastOn() throws IOException {
        if(lastDayOn <= 24 && isDecember) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
            LocalDateTime now = LocalDateTime.now();
            int currDay = Integer.parseInt(dtf.format(now));
            int daysFromLastOn = currDay - lastDayOn;
            if (daysFromLastOn >= 0) {
                updatePresentNum(daysFromLastOn);
                timer.start();
        }
        } else {
            currPresents = 0;
            totalPresents = 0;
            lastDayOn = 0;
            writeData();
            timer.start();
        }

    }
    private void openPresent() throws IOException {
        if (currPresents > 0) {
            currPresents--;
            presentNum.setText(String.valueOf(currPresents));
            presentNumBg.setFill(color1);
            generatePresent();
        } else {
            presentNumBg.setFill(color1);
        }
        writeData();
    }
    @FXML
    protected void mouseEntered() {
        clickValid = true;
    }
    @FXML
    protected void mouseExited() {
        clickValid = false;
    }
    @FXML
    protected void mousePressed() throws IOException {
        if (clickValid) {
            openPresent();
        }
    }

    private void generatePresent() {
        Gifts[] gift = Gifts.values();
        int random = (int) (Math.random() * gift.length);
        Gifts selectedGift = gift[random];
        presentName.setText(selectedGift.getPresent().getPresentName());
        presentDesc.setText(selectedGift.getPresent().getPresentDescription());
    }

    private void checkMonth() throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDateTime now = LocalDateTime.now();
        int currMonth = Integer.parseInt(dtf.format(now));
        if (currMonth == 12) {
            isDecember = true;
        } else {
            writeData();
        }
    }
    @FXML
    protected void writeData() throws IOException {
        FileWriter writer = new FileWriter("lastDayOn.json");
        Gson gson = new Gson();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        int currDay = Integer.parseInt(dtf.format(now));
        String saveData;
        if (isDecember){
            saveData = currDay+"," + currPresents + "," + totalPresents;
        } else {
            saveData = "0,0,0";
        }
        gson.toJson(saveData, writer);
        writer.close();
    }

    private void readData() throws IOException {
        FileReader reader = new FileReader("lastDayOn.json");
        Gson gson = new Gson();
        String data = gson.fromJson(reader, String.class);
        String[] splitData = data.split(",");
        lastDayOn = Integer.parseInt(splitData[0]);
        currPresents = Integer.parseInt(splitData[1]);
        totalPresents = Integer.parseInt(splitData[2]);
        presentNum.setText(String.valueOf(currPresents));
        reader.close();
    }


}

