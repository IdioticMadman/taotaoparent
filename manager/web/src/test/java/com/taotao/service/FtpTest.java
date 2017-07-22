package com.taotao.service;

import com.taotao.common.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FtpTest {

    @Test
    public void testFtp(){
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("192.168.1.105",21);
            ftpClient.login("ftpuser","123123");
            FileInputStream fileInputStream = new FileInputStream(new File("/Users/robert/Pictures/WechatIMG6.jpeg"));
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile("new.jpg",fileInputStream);
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFtpUtil(){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("/Users/robert/Pictures/WechatIMG6.jpeg"));
            FtpUtil.uploadFile("192.168.1.105",21,"ftpuser","123123","/ftpdir","/image","beautiful.jpg",fileInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
