/**
 * @author Alperen Arik
 * @version 5/29/22
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;

    /**
     * Constructeur naturel de la classe Game
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui= new UserInterface(this.aEngine);
        this.aEngine.setGUI(this.aGui);
    }
} // Game
