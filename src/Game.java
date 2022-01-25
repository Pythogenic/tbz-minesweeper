import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    @Getter
    private final int width = 20;
    private final int amountOfBombs = 30;
    @Getter
    private final List<Field> fields = new ArrayList<>();
    private UserInterface ui = new UserInterface();


    public void start() {
        generate();
        ui.generate();
    }

    public void lose() {
        for (Field field :fields) {
            if (field.getClass() == EmptyField.class) {
                ((EmptyField) field).setDisplayedNumber();
            }
            field.setVisible(true);
        }
        ui.showLostDialog();
    }

    public void reset() {
        fields.removeAll(fields);
        ui.exit();
        ui = new UserInterface();
        start();
    }

    public Field getFieldByCoordinate(Coordinate coordinate) {
        for (Field field : fields) {
            if (field.getPosition().equals(coordinate)) {
                return field;
            }
        }
        return null;
    }

    private void generate() {
        List<Coordinate> bombCoordinates = getBombCoordinates();
        for (int x = 1; x <= width; x++) {
            for (int y = 1; y <= width; y++) {
                Coordinate coordinate = new Coordinate(x,y);
                if (coordinateInList(coordinate, bombCoordinates)) {
                    fields.add(new BombField(coordinate));
                } else {
                    fields.add(new EmptyField(coordinate));
                }
            }
        }
    }

    private List<Coordinate> getBombCoordinates() {
        List<Coordinate> result = new ArrayList<>();
        for (int i = 0; i < amountOfBombs; i++) {
            Coordinate coordinate;
            do {
                int x = 0;
                int y = 0;
                try {
                    x = randomInt(1, width);
                    y = randomInt(1, width);
                } catch (IllegalArgumentException e) {
                    System.out.println("Internal error");
                }
                coordinate = new Coordinate(x, y);
            } while (coordinateInList(coordinate, result));
            result.add(coordinate);
        }
        return result;
    }

    private int randomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private boolean coordinateInList(Coordinate coordinate, List<Coordinate> list) {
        for (Coordinate element : list) {
            if (element.equals(coordinate)) {
                return true;
            }
        }
        return false;
    }

}
