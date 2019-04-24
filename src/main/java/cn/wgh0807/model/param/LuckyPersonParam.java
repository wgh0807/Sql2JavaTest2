package cn.wgh0807.model.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Wgh0807 on 2019/04/24 13:19:36.
 */

public class LuckyPersonParam implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "lucky_person";

    private Integer Id;
    private String Name;
    private BigDecimal Money;

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
    public void setMoney(BigDecimal Money){
        this.Money = Money;
    }
    public BigDecimal getMoney(){
        return Money;
    }
}
