import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Field {

    private boolean visible = false;
    private boolean markedAsBomb = false;
    private Coordinate position;

    public List<Field> getNeighbours() {
        Coordinate compareCoordinate = new Coordinate(position.getX(), position.getY()); //compare coordinate is position of this instance
        List<Field> result = new ArrayList<>();

        for (int i = 0; i < 3; i++) { //loop trough left, right and same
            compareCoordinate.setX(compareCoordinate.getX()-1+i);
            for (int j = 0; j < 3; j++) { //loop trough up, down and same
                compareCoordinate.setY(compareCoordinate.getY()-1+j);
                for (Field field: MinesweeperApplication.game.getFields()) { //loop trough fields if matches
                    if (field.position.equals(compareCoordinate) && field != this) { //if match and not this instance
                        result.add(field);
                    }
                }
            }
        }

        return result;
    }

    public abstract void check();

    public void toogleMarkedAsBomb() {
        markedAsBomb = !markedAsBomb;
    }

}
