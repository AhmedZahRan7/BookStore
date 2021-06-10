package sample.models;

import java.util.Date;

public class CreditCard {

    private String ID;  // credit card number
    private Date expireDate;
    private User owner;

    public CreditCard(String ID, User owner, Date expireDate) {
        this.ID = ID;
        this.expireDate = expireDate;
        this.owner = owner;
    }

    public boolean validateNumber() {
        long intForm;
        try {
            intForm = Long.parseUnsignedLong(this.ID);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return validateDate();
    }

    private boolean validateDate() {
        Date now = new Date();
        if (now.compareTo(getExpireDate()) != -1)
            return false;
        return true;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
