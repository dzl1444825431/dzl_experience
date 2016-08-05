package com.dzl.groovy.android;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DimensTools {
     static String oldFilePath = "C:/Users/Administrator/Desktop/layoutroot/dimens.xml";
     
     static String filePathHdpi="C:/Users/Administrator/Desktop/layoutroot/dimensHdpi.xml";
     
  
     static float changes = 1.125f;
     public static void main(String[] args) {
          String st = convertStreamToString(oldFilePath, changes);
          DeleteFolder(filePathHdpi);
          writeFile(filePathHdpi, st);
     }
     public static String convertStreamToString(String filepath, float f) {
          StringBuilder sb = new StringBuilder();
          try {
               BufferedReader bf = new BufferedReader(new FileReader(filepath));
               String line = null;
               System.out.println("q1");
               String endmark = "dp</dimen>";
               String startmark = ">";
               while ((line = bf.readLine()) != null) {
                    if (line.contains(endmark)) {
                         int end = line.lastIndexOf(endmark);
                         int start = line.indexOf(startmark);
                         String stdp = line.substring(start + 1, end);
                         //int dp = Integer.parseInt(stpx);
                         float dp=Float.parseFloat(stdp);
                         //float newdp =  ((float) dp / f);
                         
                         System.out.println("dp:"+dp);
                         
                         float newdp=dp/f;
                         
                         System.out.println("newdp:"+newdp);
                         
                         String dpStr=String.valueOf(dp);
                         String newline;
                         if(dpStr.contains(".0")){
                             int x=dpStr.indexOf(".");
                             System.out.println("x:"+x);
                             dpStr= dpStr.substring(0,x);
                             newline= line.replace(dpStr + "dp", newdp + "dp");
                         }else{
                             newline = line.replace(dp + "dp", newdp + "dp");
                         }
                         
                         System.out.println("newline:"+newline);
                         sb.append(newline + "\r\n");
                    } else {
                         sb.append(line + "\r\n");
                    }
               }
              // System.out.println(sb.toString());
          } catch (IOException e) {
               e.printStackTrace();
          }
          return sb.toString();
     }
     public static boolean DeleteFolder(String sPath) {
          File file = new File(sPath);
          if (!file.exists()) { 
               return true;
          } else {
               if (file.isFile()) { 
                    return deleteFile(sPath);
               } else { 
               // return deleteDirectory(sPath);
               }
          }
          return false;
     }
     public static void writeFile(String filepath, String st) {
          try {
               FileWriter fw = new FileWriter(filepath);
               BufferedWriter bw = new BufferedWriter(fw);
               bw.write(st);
               bw.flush();
               bw.close();
          } catch (IOException e) {
               e.printStackTrace();
          }
     }
     public static boolean deleteFile(String sPath) {
          boolean flag = false;
          File file = new File(sPath);
          if (file.isFile() && file.exists()) {
               file.delete();
               flag = true;
          }
          return flag;
     }
}