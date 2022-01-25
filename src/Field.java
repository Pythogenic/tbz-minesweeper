import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Field {

    private boolean visible = false;
    private boolean markedAsBomb = false;
    private Coordinate position;

    public Field(Coordinate position) {
        this.position = position;
    }

    public List<Field> getNeighbours() {
        List<Field> result = new ArrayList<>();
        List<Field> allFields = MinesweeperApplication.game.getFields();
        for (Field field : allFields) {
            int xDistance = field.position.getX() - this.position.getX();
            int yDistance = field.position.getY() - this.position.getY();
            if(xDistance < 2 && xDistance > -2 && yDistance < 2 && yDistance > -2) {
                if (!field.position.equals(this.position)) {
                    result.add(field);
                }
            }
        }
        if (result.size() != 8 && result.size() !=5 && result.size() != 3) {
            System.out.println("Something went wrong: Number of neighbours is " + result.size());
            System.out.println(result);
        }
        return result;
    }



    public abstract void check();

    public void toogleMarkedAsBomb() {
        markedAsBomb = !markedAsBomb;
    }

}
