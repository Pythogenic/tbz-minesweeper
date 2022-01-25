public class BombField extends Field{

    public BombField(Coordinate position) {
        super(position);
    }

    public void check() {
        setVisible(true);
        MinesweeperApplication.game.lose();
    }

}
