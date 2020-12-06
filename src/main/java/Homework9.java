package main.java;

import main.java.homework08.search.RedBlackTree;

import java.io.*;
import java.util.Iterator;


public class Homework9 {
    public static void main(String[] args) {
        RedBlackTree<String> storageOfCityName=new RedBlackTree<>();
        File file = new File("C:\\Users\\bovae\\IdeaProjects\\DynamicArray\\src\\strings\\city.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while((str= br.readLine())!=null){
                storageOfCityName.put(str);
            }
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            str=reader.readLine();
            Iterator<String> it=storageOfCityName.iterator();
            int i;
            String s;
            while (it.hasNext()){
                s=it.next();
                i=s.substring(0,str.length()).compareToIgnoreCase(str);
                if(i==0){
                    System.out.println(s);
                }
                else if(i>0){
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
