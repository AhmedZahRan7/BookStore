package sample.models;

import java.sql.Date;

public class CreditCard {

    private String card_id;  // credit card number
    private Date exp_date;
    private String user_name;

    public CreditCard(String ID, String owner, Date expireDate) {
        this.card_id = ID;
        this.exp_date = expireDate;
        this.user_name = owner;
    }

    public CreditCard() {

    }

    public boolean validateNumber() {
        long intForm;
        try {
            intForm = Long.parseUnsignedLong(this.card_id);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return validateDate();
    }

    private boolean validateDate() {
        Date now = Date.valueOf(java.time.LocalDate.now());
        if (now.compareTo(getExp_date()) != -1)
            return false;
        return true;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public Date getExp_date() {
        return exp_date;
    }

    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }

    public String getOwner() {
        return user_name;
    }

    public void setOwner(String owner) {
        this.user_name = owner;
    }
}
