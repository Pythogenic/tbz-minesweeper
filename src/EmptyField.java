import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EmptyField extends Field{

    private int displayNumber = 0;

    public EmptyField(Coordinate position) {
        super(position);
    }

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
        int newNumber = 0;
        for (Field field : getNeighbours()) {
            if (field.getClass() == BombField.class) {
                newNumber++;
            }
        }
        displayNumber = newNumber;
    }

    private void showEmptyNeighboursWithDisplayNumber0(List<Field> alreadyFields) {
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
