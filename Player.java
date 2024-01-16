import java.util.HashSet;

/**
 * @author Alperen Arik
 * @version 5/29/22
 */
public class Player
{
    private String aName;
    private double aWeight;
    private Room aCurrentPosition;
    private Room aPreviousPosition;
    private HashSet<Item> aItemInventory;

    public Player(final String pName, final Room pCurrentPosition)
    {
        this.aName=pName;
        this.aCurrentPosition=pCurrentPosition;
        this.aWeight=80;
        this.aItemInventory = new HashSet<Item>();
    }

    public String getName()
    {
        return this.aName;
    }

    public double getWeight()
    {
        return this.aWeight;
    }

    public Room getCurrentPosition()
    {
        return this.aCurrentPosition;
    }

    public Room getPreviousPosition()
    {
        return this.aPreviousPosition;
    }

    public void setName(final String pName)
    {
        this.aName = pName;
    }

    public void setWeight(final double pWeight)
    {
        this.aWeight=pWeight;
    }

    public void setCurrentPosition(final Room pCurrentPosition)
    {
        this.aCurrentPosition=pCurrentPosition;
    }

    public void setPreviousPosition(final Room pPreviousPosition)
    {
        this.aPreviousPosition= pPreviousPosition;
    }
}
