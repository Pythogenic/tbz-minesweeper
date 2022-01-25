public class BombField extends Field{

    public void check() {
        MinesweeperApplication.game.lose();
    }

}
