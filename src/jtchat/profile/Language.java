
package jtchat.profile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

/*   String Table: 
 *      reference language/en-US.ini
 */

public class Language {
    private static Language ins=null;
    private HashMap<String,String> translate;
    private Vector<LanguageChangeListener> languageChangeListener;
    private String currentLangCode;
    
    public Language(){
        languageChangeListener = new Vector<LanguageChangeListener>();
    }
    
    public static Language ins(){
        if(ins==null){
            ins = new Language();
        }
        return ins;
    }
    
    public String get(String entryName){
        String value = translate.get(entryName);
        return value==null?"(error)":value;
    }
    
    public boolean loadDefaultLanguage(){
        Scanner sc=null;
        try{
            sc = new Scanner(new File("language.ini"));
        }catch(FileNotFoundException e){
            System.err.printf("file not found\n");
            return false;
        }
        currentLangCode = sc.nextLine();
        sc.close();
        return load(new File("./language/"+currentLangCode+".ini"));
    }
    
    public String getCurrentLangCode(){
        return currentLangCode;
    }
    
    public boolean load(File langFile){
        translate = new HashMap<String,String>();
        //apply change
        Scanner sc=null;
        try{
            sc = new Scanner(langFile,"UTF-8");
        }catch(FileNotFoundException e){
            System.err.printf("file not found\n");
            return false;
        }
        String line;

        while(sc.hasNext()){
            line = sc.nextLine();
            String[] split = line.split("=",2);
            translate.put(split[0], split[1]);
        }
        sc.close();
        
        for(int ix=0;ix<languageChangeListener.size();ix++){
            languageChangeListener.get(ix).languageChange();
        }
        //delete texts
        String filename = langFile.getName();
        currentLangCode = filename.substring(0, filename.indexOf('.'));
        translate = null;
        
        return true;
    }
    
    public void registerLogListener(LanguageChangeListener listener){
        languageChangeListener.add(listener);
    }
    
    public void removeLogListener(LanguageChangeListener listener){
        languageChangeListener.remove(listener);
    }
    
    public void save(){
        Language.save(this.currentLangCode);
    }
    
    public static boolean createDefaultLanguageIni(){
        return save("en-US");
    }
    
    
    
    public static boolean save(String langCode){
        FileWriter fstream = null;
        try{
            fstream = new FileWriter("language.ini");
        }catch(java.io.FileNotFoundException e){
            //cannot create file or don't have permission to write
            System.err.println("Error: cannot create output file or don't have permission to write");
            return false;
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            return false;
        }
        
        BufferedWriter fout = new BufferedWriter(fstream);
        try {
            fout.write(langCode);
            fout.close();
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            return false;
        }
        return true;
    }
}
