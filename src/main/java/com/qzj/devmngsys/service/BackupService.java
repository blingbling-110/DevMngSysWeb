package com.qzj.devmngsys.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BackupService {
    public List<String> search(){
        List<String> backupFiles = new ArrayList<>();
        File file = new File("backup");
        File[] files = file.listFiles();
        if (files != null && files.length != 0)
        for (File f: files){
            if (f.getName().endsWith(".sql"))
                backupFiles.add(f.getName());
        }
        return backupFiles;
    }

    public String doBackup(String username, String pwd, String url){
        url = url.replace("//", "");
        String database = url.substring(url.indexOf("/") + 1, url.indexOf("?"));
        Date date = new Date();//获取当前毫秒值
        /*
        设置当前时间的输出格式

        SimpleDateFormat：
        	是一个以国别敏感的方式格式化和分析数据的具体类。
        	它允许格式化 (date -> text)、语法分析 (text -> date)和标准化。
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        File dir = new File("backup");
        if (!dir.exists())
            dir.mkdirs();
        String backupPath = "backup/" + database + "_" + sdf.format(date) + ".sql";
        String cmd = String.format("mysqldump -u%s -p%s %s > %s", username, pwd, database, backupPath);
        try {
            if (System.getProperty("os.name").contains("Windows"))
                Runtime.getRuntime().exec(new String[]{"cmd", "/C", cmd});
            else
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});
            return backupPath;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public int restore(String username, String pwd, String url, String fileName){
        url = url.replace("//", "");
        String database = url.substring(url.indexOf("/") + 1, url.indexOf("?"));
        String cmd = String.format("mysql -u%s -p%s %s < %s", username, pwd, database, "backup/" + fileName);
        try {
            if (System.getProperty("os.name").contains("Windows"))
                Runtime.getRuntime().exec(new String[]{"cmd", "/C", cmd});
            else
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void delete(String fileName) {
        File file = new File("backup/" + fileName);
        file.delete();
    }
}
