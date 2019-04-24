package cn.wgh0807.model.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Wgh0807 on 2019/04/24 13:19:36.
 */

public class CuteCatParam implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "cute_cat";

    private Integer Id;
    private String Name;
    private String Sex;
    private Integer Age;

    public void setId(Integer Id){
        this.Id = Id;
    }
    public Integer getId(){
        return Id;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return Name;
    }
    public void setSex(String Sex){
        this.Sex = Sex;
    }
    public String getSex(){
        return Sex;
    }
    public void setAge(Integer Age){
        this.Age = Age;
    }
    public Integer getAge(){
        return Age;
    }
}
