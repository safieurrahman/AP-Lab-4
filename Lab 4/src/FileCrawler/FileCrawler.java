package FileCrawler;

import java.util.*;
import java.io.*;


public class FileCrawler {

    //adds folders and finds files
    public static void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                addTree(child, all);
            }
        }else{
            all.add(file);
        }
    }
    
    //search all the files in the map for a particular string
    public static void searchIndex(HashMap MyFiles) throws IOException{
        //for user input
        Scanner in = new Scanner(System.in);
        
        while(true){
            System.out.println("Enter the keyword you want to search for, '-1' to exit: ");
            String s = in.nextLine();
            //exit method
            if(s.equals("-1")){
                return;
            }
            
            //to traverse the map
            Set set = MyFiles.entrySet();
            Iterator i = set.iterator();
            
            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                String val = (String)me.getValue();
                
                File file = new File(val);
                
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = null;
                while ((line = br.readLine()) != null) {
                        if(line.contains(s)){
                          System.out.println("The string exists in file: "+" "+val);
                        }
                }
     
            }   
        }
    }
    
    public static void printIndex(HashMap MyFiles){
        
            Set set = MyFiles.entrySet();
            Iterator s = set.iterator();
            System.out.println("The Index mapping is: ");
            while(s.hasNext()) {
                Map.Entry me = (Map.Entry)s.next();
                System.out.print(me.getKey() + ": ");
                System.out.println(me.getValue());
            }
        
    }
    

    public static void main(String[] args) {
      
    Collection<File> all = new ArrayList<File>();
    addTree(new File("D:/Test"), all); //Random directory to test

    try {
        FileWriter fstream = null;
        fstream = new FileWriter("result.txt");
        BufferedWriter out = new BufferedWriter(fstream);

        //creating HashMap for indexing
        
        HashMap<String,String> MyFiles = new HashMap();
               
        Iterator itr = all.iterator();
        while(itr.hasNext()){
            //get file
            File tested = (File) itr.next();

            //get the filename
            String Fname = tested.getName();
            String Fpath = tested.getPath();
            
   
            //write output to a file
            out.write(Fname+"         "+Fpath);
            out.newLine();
            
            //putting files to hashmap
            MyFiles.put(Fname,Fpath);
                   
        }
            
        //print indexed files
        printIndex(MyFiles);
        //search the index
        searchIndex(MyFiles);
        
        out.close();
        }catch(IOException ex){}
                
    }
    
}

