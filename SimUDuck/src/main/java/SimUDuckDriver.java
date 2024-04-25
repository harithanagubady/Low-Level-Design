import com.simuduck.v1.Duck;
import com.simuduck.v1.MallardDuck;
import com.simuduck.v1.RedHeadDuck;
import com.simuduck.v1.RubberDuck;

public class SimUDuckDriver {

    public static void main(String[] args) {
        Duck duck = new RubberDuck();
        duck.display();
        duck = new MallardDuck();
        duck.display();
        duck = new RedHeadDuck();
        duck.display();
    }
}
