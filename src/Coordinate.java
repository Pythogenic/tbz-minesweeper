import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Coordinate {

    private int x;
    private int y;

    public boolean equals(Coordinate coordinate) {
        return coordinate.x == this.x && coordinate.y == this.y;
    }

}
