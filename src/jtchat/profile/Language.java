
package jtchat.profile;

/*   String Table:
 *   bConnect
 *   bDisconnect
 * 
 */


public class Language {
    private static Language ins=null;
    
    public static Language ins(){
        if(ins==null){
            ins = new Language();
        }
        return ins;
    }
    
    public String get(String entryName){
        return "";
    }
    
    public void load(String langCode){
        //
    }
}
