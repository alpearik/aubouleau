/**
 * @author Alperen Arik
 * @version 5/29/22
 */
public class Item
{
    private String aDescription;
    private double aWeight;
    private String aName;

    public Item(final String pDescription, final double pWeight)
    {
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }
    public String getDescription()
    {
        return this.aDescription;
    }
    public double getWeight()
    {
        return this.aWeight;
    }
    public String getName()
    {
        return this.aName;
    }
    public void setDescription(final String pDescription)
    {
        this.aDescription = pDescription;
    }
    public void setWeight(final double pWeight)
    {
        this.aWeight = pWeight;
    }
    public void setName(final String pName)
    {
        this.aName = pName;
    }
    public String toString()
    {
        return this.aName + "("+ this.aWeight + "kg" + ")";
    }
}
