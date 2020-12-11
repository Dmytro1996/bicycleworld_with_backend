/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service.impl;

import com.mycompany.bicycleworld.service.HomeService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class HomeServiceImpl implements HomeService {
    Logger logger=LoggerFactory.getLogger(HomeService.class);
    public String getArticle(File file){
        String article="";        
        /*try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(
                "C:\\Users\\dmytr\\OneDrive\\Documents\\NetBeansProjects\\bicycleworld"
                        + "\\bicycleworld\\src\\main\\resources\\articles\\"+articleName+".txt"),StandardCharsets.UTF_8))){
            String line=null;
            while((line=br.readLine())!=null){
                article+=line;
            }
            if(article.length()==0){
                logger.info("article is empty");
            }
        } catch(IOException e){
            logger.info(e.getMessage());
        }*/
        //logger.info(file.getAbsolutePath());
        try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8))){
            String line=null;
            while((line=br.readLine())!=null){
                article+=line;
            }
            if(article.length()==0){
                logger.info("article is empty");
            }   
        } catch(IOException e){
            logger.info(e.getMessage());
        }
        return article;
        //return article;
    }
}
