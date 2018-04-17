package cn.abtion.taskgo.mvp.model.task.model;

/**
 * @author FanHongyu.
 * @since 18/4/17 20:30.
 * email fanhongyu@hrsoft.net.
 */

public class CardsModel {

    private String cardOne;
    private String cardTwo;
    private String cardThree;


    public CardsModel(String cardOne, String cardTwo, String cardThree) {
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
        this.cardThree = cardThree;
    }


    public String getCardOne() {
        return cardOne;
    }

    public void setCardOne(String cardOne) {
        this.cardOne = cardOne;
    }

    public String getCardTwo() {
        return cardTwo;
    }

    public void setCardTwo(String cardTwo) {
        this.cardTwo = cardTwo;
    }

    public String getCardThree() {
        return cardThree;
    }

    public void setCardThree(String cardThree) {
        this.cardThree = cardThree;
    }


    @Override
    public String toString() {
        return "{" + "1" + cardOne + ','
                + "2" + cardTwo + ','
                + "3" + cardThree + '}';
    }
}
