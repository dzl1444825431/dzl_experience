//package com.dzl2;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.nio.channels.FileChannel;
//import java.nio.channels.FileLock;
//
//public class ThreadKeepTools {
//    /**
//    * 通过文件锁来判断程序是否正在运行
//    * @return 如果正在运行返回true，否则返回false
//    */
//    private static boolean isRunning(String fileLockPath)
//    {
//        boolean rv=false;
//        try {
//            //
//            String os_name=System.getProperty("os.name");
//            //指定文件锁路径
//            String path=null;
//            if(os_name.indexOf("Windows")>-1)
//            {
//                //如果是Windows操作系统
//                path=System.getProperty("user.home")+System.getProperty("file.separator");
//            }
//            else
//            {
//                path="/usr/temp/";
//            }
//            File dir=new File(path);
//            if(!dir.exists())
//            {
//                dir.mkdirs();
//            }
//            //程序名称
//            String applicationName="run.bat";
//            RandomAccessFile fis = new RandomAccessFile(fileLockPath,"rw");
//            FileChannel lockfc = fis.getChannel();
//            FileLock flock = lockfc.tryLock();
//            if(flock == null) {
//                System.out.println("程序正在运行.");
//                rv=true;
//                
//            }else {
//                flock.release();
//            }
//            
//         
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        return rv;
//    }
//    
//    public static void ExcuteThread() throws InterruptedException{
//        String strFilePath=BaseDataofConfiguration.baseUrlOfPro+"testThread.txt";
//        String strBatPath=BaseDataofConfiguration.baseUrlOfPro+"run.bat";
//        strBatPath=strBatPath.replaceFirst("/", "");
//        while(true){
//            System.out.println("strFilePath:"+strFilePath);
//        boolean RunOrNOT=isRunning(strFilePath);
//        System.out.println("RunOrNot:"+RunOrNOT);
//        
//        if(!RunOrNOT){
//            try {
//                System.out.println("开始执行程序！");
//            
//                Runtime.getRuntime().exec("cmd /k start "+strBatPath);
//                System.out.println("strbatpath:"+strBatPath);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } 
//            
//        }else {
//            Thread.sleep(6000);
//        }
//        Thread.sleep(10000);
//        
//        }
//    }
//    public static void main(String[] args) {
//        try {
//            ExcuteThread();
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//}