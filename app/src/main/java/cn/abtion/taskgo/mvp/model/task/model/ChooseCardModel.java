package cn.abtion.taskgo.mvp.model.task.model;

import java.io.Serializable;

/**
 * @author FanHongyu.
 * @since 18/4/20 12:50.
 * email fanhongyu@hrsoft.net.
 */

public class ChooseCardModel implements Serializable {


    private String cardName;
    private String cardPrice;
    private int haveNumber;
    private int chooseNumber;
    private String cardInfo;
    private String cardImgUrl;


    public int getChooseNumber() {
        return chooseNumber;
    }

    public void setChooseNumber(int chooseNumber) {
        this.chooseNumber = chooseNumber;
    }

    public String getCardImgUrl() {
        return cardImgUrl;
    }

    public void setCardImgUrl(String cardImgUrl) {
        this.cardImgUrl = cardImgUrl;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(String cardPrice) {
        this.cardPrice = cardPrice;
    }

    public int getHaveNumber() {
        return haveNumber;
    }

    public void setHaveNumber(int haveNumber) {
        this.haveNumber = haveNumber;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }
}
