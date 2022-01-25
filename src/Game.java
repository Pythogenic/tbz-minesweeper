import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private final int amountOfX = 10;
    private final int amountOfY = 10;
    private final int amountOfBombs = 6;
    @Getter
    private final List<Field> fields = new ArrayList<>();
    private final UserInterface ui = new UserInterface();

    public void start() {
        generate();
        ui.generate(amountOfX, amountOfY);
    }

    public void lose() {
        //todo ui message
        reset();
    }

    public void reset() {
        fields.removeAll(fields);
        generate();
        //todo ui reset
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
        for (int x = 1; x <= amountOfX; x++) {
            for (int y = 1; y <= amountOfY; y++) {
                Coordinate coordinate = new Coordinate(x,y);
                if (coordinateInList(coordinate, bombCoordinates)) {
                    fields.add(new BombField());
                } else {
                    fields.add(new EmptyField());
                }
            }
        }
    }

    private List<Coordinate> getBombCoordinates() {
        List<Coordinate> result = new ArrayList<>();
        for (int i = 0; i < amountOfBombs; i++) {
            Coordinate coordinate;
            do {
                int x = randomInt(1, amountOfX);
                int y = randomInt(1, amountOfY);
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
