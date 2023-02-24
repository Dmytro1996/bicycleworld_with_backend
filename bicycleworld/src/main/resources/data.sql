/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  dmytr
 * Created: 9 груд. 2020
 */
/*INSERT INTO Articles(id,direction) VALUES(1,"C:\\Users\\dmytr\\OneDrive\\Documents\\NetBeansProjects\\bicycleworld\\bicycleworld\\src\\main\\resources\\articles\\Article about choosing a bicycle.txt");*/
INSERT INTO Articles(id,direction) VALUES(1,"src\\main\\resources\\articles\\Article about choosing a bicycle.txt");
INSERT INTO Articles(id,direction) VALUES(2,"src\\main\\resources\\articles\\Article about usefullness of riding a bicycle.txt");

INSERT INTO Users(id,full_name,email,password, auth_provider) VALUES(1,"John","john@mail.com","$2y$12$HyKB5hJ9pLLAGQ4ff8GJB.pLWGoF.7Kout7v2w4E0.JswWKCRtwc.", "CUSTOM");
INSERT INTO Users(id,full_name,email,password, auth_provider) VALUES(2,"George","george@mail.com","$2y$12$VgTPP8rl2yYPNC0eVOo5SO4845lCChJ7u8319n2CxT4vMZqFbzj2W", "CUSTOM");
INSERT INTO Users(id,full_name,email,password, auth_provider) VALUES(3,"Sam","sam@mail.com","$2y$12$u3ReBjbei.RMzq6pY6c1XuYWCRBmlsFH77g/CkN8CJWVZeVULQKtS ", "CUSTOM");

INSERT INTO Comments(id,comment,user_id,article_id) VALUES(1,"Now I know what bicycle to choose",1,1);
INSERT INTO Comments(id,comment,user_id,article_id) VALUES(2,"Very useful article",2,1);
INSERT INTO Comments(id,comment,user_id,article_id) VALUES(3,"It helped me a lot",3,1);
INSERT INTO Comments(id,comment,user_id,article_id) VALUES(4,"Now I will ride a bike more often",1,2);
INSERT INTO Comments(id,comment,user_id,article_id) VALUES(5,"Interesting article",2,2);