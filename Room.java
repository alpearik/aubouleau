import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Alperen Arik
 * @version 5/29/22
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room> exits;
    private String aImageName;
    private HashSet<Item> aItem;
    /**
     * Constructeur naturel de la classe Room
     * @param pDescription la description du lieu
     * @param pImage l'image associé à la room
     */
    public Room(final String pDescription, final String pImage)
    {
        this.aDescription=pDescription;
        exits = new HashMap<String, Room>();
        this.aImageName = pImage;
        aItem = new HashSet<Item>();
    }

    /**
     * @return la description d'une room
     */
    public String getDescription()
    {
        return this.aDescription;
    }

    /**
     * @param vDirection est la direction
     * @param neghbor est la salle dans la direction
     */
    public void setExit(final String vDirection, final Room neghbor)    
    {
        exits.put(vDirection, neghbor);
    }

    public void addItem(final Item pItem)
    {
        aItem.add(pItem);
    }

    public void deleteItem(final String pName)
    {
        aItem.remove(pName);
    }

    public String getItemString()
    {
        String ItemString="";
        Iterator ItemIterator=aItem.iterator();
        while(ItemIterator.hasNext())
            ItemString += ((Item)ItemIterator.next()).getDescription();
        return ItemString;
    }

    /**
     * @return la valeur d'une sortie
     * @param vDirection est la direction
     */
    public Room getExit(final String vDirection)
    {
        return exits.get(vDirection);
    }

    /**
     * @return une String qui possede les différentes sorties possibles
     */
    public String getExitString()
    {
        String Exit="Exits : ";
        if(getExit("north") != null)
        {
            Exit+="north ";
        }
        if(getExit("south") != null)
        {
            Exit+="south ";
        }
        if(getExit("west") != null)
        {
            Exit+="west ";
        }
        if(getExit("east") != null)
        {
            Exit+="east ";
        }
        return Exit;
    }

    public String getLongDescription()
    {
        if(getItemString()!="")
        {
            return "You are " + getDescription() +".\n"+"Items : "+getItemString()+".\n" + getExitString();
        }
        else
        {
            return "You are " + getDescription() +".\n"+"No item here."+"\n" + getExitString();
        }
    }

    public String getImageName()
    {
        return this.aImageName;
    }
} // Room
