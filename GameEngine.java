import java.util.Stack;
import java.util.Scanner;
import java.io.File;

/**
 * @author Alperen Arik
 * @version 5/29/22
 */
public class GameEngine
{

    private Room aCurrentRoom;
    private Parser aParser;
    private UserInterface aGui;
    private Room aPreviousRoom;
    private Player aPlayer;
    /**
     * Créee les différentes room et initialise leurs sorties possibles
     */
    private void createRooms()
    {
        Room vHouse =new Room("inside your home", "maison.png");
        Room vFarm =new Room("in a farm","ferme.png");
        Room vMountain =new Room("in the mountains","montagne.png");
        Room vHut =new Room("in a hut","cabane.png");
        Room vLake =new Room("in a lake","lac.png");
        Room vForest=new Room("inside the forest","foret.png");
        Room vAttic=new Room("in the attic","grenier.png");
        vHouse.setExit("south", vLake);
        vHouse.setExit("west", vFarm);
        vHouse.setExit("east", vHut);
        vHouse.setExit("north", vAttic);
        vAttic.setExit("south", vHouse);
        vHut.setExit("west", vHouse);
        vLake.setExit("north", vHouse);
        vFarm.setExit("north", vMountain);
        vFarm.setExit("west", vForest);
        vFarm.setExit("east", vHouse);
        vMountain.setExit("south",vFarm);
        vForest.setExit("east",vFarm);
        
        this.aCurrentRoom=vHouse;
        vLake.addItem(new Item("a long rope",0.5));
        vFarm.addItem(new Item("a warm coat",1));
        vHut.addItem(new Item("a climbing equipment",2));
        vAttic.addItem(new Item("a golden axe",3));
        vHouse.addItem(new Item("a axe",1));
        vHut.addItem(new Item("a magic cookie",0.3));
    }

    /**
     * Constructeur naturel de la classe Game
     */
    public GameEngine()
    {
        createRooms();
        this.aParser= new Parser();
        this.aPlayer=new Player("Player",aCurrentRoom);
    }

    /**
     * Permet de se déplacer dans différentes room à partir du second mot(si
     * il existe) et affiche les sorties possibles
     * @param pCommand est la commande entrée par l'utilisateur
     */
    private void goRoom(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("Go where ?");
            return;
        }
        String vDirection= pCommand.getSecondWord();
        Room vNextRoom = this.aPlayer.getCurrentPosition().getExit(vDirection);
        if(vNextRoom==null)
        {
            this.aGui.println("There is no door !");
        }
        else
        {
            this.aPlayer.setPreviousPosition(this.aPlayer.getCurrentPosition());
            this.aPlayer.setCurrentPosition(vNextRoom);
            printLocationInfo();
        }
    }
    public void setGUI(final UserInterface pUserInterface)
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * Affiche le message de bienvenue
     */
    private void printWelcome()
    {
        this.aGui.println("Welcome to the World of Zuul!");
        this.aGui.println("World of Zuul is a new, incredibly boring adventure game.");
        this.aGui.println("Type 'help' if you need help.");
        this.aGui.print("\n");
        printLocationInfo();
    }

    /**
     * Affiche les différentes commandes lorsque le joueur écrit help
     */
    private void printHelp()
    {
        this.aGui.println("You are lost. You are alone.");
        this.aGui.println("Your command words are:");
        this.aGui.println(aParser.getCommandString());
    }

    /**
     * Quitte le jeu
     * @param pCommand est la commande entrée par l'utilisateur
     * @return true si la commande a un second mot sinon renvoie false
     */
    private boolean quit(final Command pCommand)
    {
        if(pCommand.hasSecondWord())
        {
            System.out.println("Quit what ?");
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Appele la méthode en fonction de la commande
     * @param pCommand est la command entrée par l'utilisateur
     */
    public void processCommand(final String pCommand)
    {
        this.aGui.println("> " + pCommand); 
        Command Commande = this.aParser.getCommand(pCommand); 
        if(Commande.isUnknown())
        {
            this.aGui.println("I don't know what you mean...");
            return;
        }
        String CommandeWord = Commande.getCommandWord();

        if(CommandeWord.equals("go"))
        {
            goRoom(Commande);
        }
        else if(CommandeWord.equals("help"))
        {
            printHelp();
        }
        else if(CommandeWord.equals("quit"))
        {
            if(Commande.hasSecondWord())
            {
                this.aGui.println("Quit what?" );
            }else
            {
                this.endGame();
            }
        }
        else if(CommandeWord.equals("look"))
        {
            look();
        }
        else if(CommandeWord.equals("eat"))
        {
            eat(Commande);
        }
        else if(CommandeWord.equals("back"))
        {
            back();
        }
        else if(CommandeWord.equals("test"))
        {
            test(Commande);
        }
        else if(CommandeWord.equals("take"))
        {
            return;
        }
        else if(CommandeWord.equals("drop"))
        {
            return;
        }
    }

    /**
     * Lance le jeu
     */
    public void endGame()
    {
        this.aGui.println("Thank you for playing. Good bye.");
        this.aGui.enable(false);
    }

    /**
     * Affiche la description du lieu
     */

    private void printLocationInfo()
    {
        this.aGui.println(this.aPlayer.getCurrentPosition().getLongDescription());
        this.aGui.showImage(this.aPlayer.getCurrentPosition().getImageName());
    }
    private void look()
    {
        this.aGui.println(this.aPlayer.getCurrentPosition().getLongDescription());
    }
    private void back()
    {
        this.aPlayer.setCurrentPosition(this.aPlayer.getPreviousPosition());
        printLocationInfo();
    }
    public void test(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("Test what?");
            return;
        }
        try{
            File vFile = new File(pCommand.getSecondWord()+".txt");
            Scanner Scan = new Scanner(vFile);
            while(Scan.hasNextLine())
            {
                processCommand(Scan.nextLine());
            }
            Scan.close();
        }
        catch(final java.io.FileNotFoundException pE){
            this.aGui.println("File not found");
        }
    }
    public void take(final String pItem)
    {
        this.aPlayer.getCurrentPosition().deleteItem(pItem);
    }
    public void drop(final Item pItem)
    {
        this.aPlayer.getCurrentPosition().addItem(pItem);
    }
    public void eat(final Command pCommand)
    {
        String ItemToEat = pCommand.getSecondWord();
        if(ItemToEat.equals("magiccookie"))
        {
            this.aGui.println("You have eaten a magic cookie, you can now carry more weight");
        }
        else
        {
            this.aGui.println("You have eaten and you are not hungry anymore");
        }
    }
} // Game
