import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EmptyField extends Field{

    private int displayNumber = 0;

    public void check() {
        setDisplayedNumber();
        setVisible(true);
        if (displayNumber == 0) {
            List<Field> alreadyFields = new ArrayList<>();
            alreadyFields.add(this);
            showEmptyNeighboursWithDisplayNumber0(alreadyFields);
        }
    }

    public void setDisplayedNumber() {
        for (Field field : getNeighbours()) {
            if (field.getClass() == BombField.class) {
                displayNumber++;
            }
        }
    }

    private void showEmptyNeighboursWithDisplayNumber0(List<Field> alreadyFields) { //todo could have a lot of bugs
        for (Field field : getNeighbours()) {
            if (field.getClass() == EmptyField.class && !alreadyFields.contains(field)) {
                EmptyField emptyField = (EmptyField) field;
                emptyField.setDisplayedNumber();
                field.setVisible(true);
                alreadyFields.add(field);
                if (emptyField.displayNumber == 0) {
                    emptyField.showEmptyNeighboursWithDisplayNumber0(alreadyFields);
                }
            }
        }
    }

}
