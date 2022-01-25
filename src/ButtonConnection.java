import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.*;

@AllArgsConstructor
@Getter
public class ButtonConnection {
    private JButton button;
    private Field field;
}