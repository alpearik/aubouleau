/**
 * @author Alperen Arik
 * @version 5/29/22
 */

public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    /**
     * Constructeur naturel de la classe Command
     * @param pCommandWord est le premier mot
     * @param pSecondWord est le second mot
     */
    public Command(final String pCommandWord,final String pSecondWord)
    {
        this.aCommandWord=pCommandWord;
        this.aSecondWord=pSecondWord;
    }

    /**
     * @return le premier mot d'une commande
     */
    public String getCommandWord()
    {
        return this.aCommandWord;
    }

    /**
     * @return le second mot d'une commande
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    }

    /**
     * @return si une commande a un second mot ou non
     */
    public boolean hasSecondWord()
    {
        return!(this.aSecondWord==null);
    }

    /**
     * @return si une commande est connue ou non
     */
    public boolean isUnknown()
    {
        return(this.aCommandWord==null);
    }
} // Command
