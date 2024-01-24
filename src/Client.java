
public class Client {

    private String name;
    private boolean broken;
    
    public Client(String name){
        this.name = name;
    }

    public Client(){    
        name = "";
    }

    /**    
     * Returns a clients name.
     * @return String name of client
     */
    public String getName(){
        return name;
    }

    /**    
     * Returns if the client is broken.
     * @return boolean of client's broken status
     */
    public boolean isBroken(){
        return broken;
    }

    /**    
     * Sets a clients name.
     * @return void
     */
    public void setName(String name){
        this.name = name;
    }

    /**    
     * Sets a clients broken status. If broken, the broken boolean will be true
     * @return void
     */
    public void setBroken(boolean broken){
        this.broken = broken;
    }

    /**    
     * @return String name of client
     */
    public String toString(){
        return name;
    }


    
}
