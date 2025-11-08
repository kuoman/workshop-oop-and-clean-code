package Workshop.Examples.Example2;

import Workshop.Examples.Example2.Other.Contact;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Account {
    private String id;
    private String name;
    private String tier;
    private BigDecimal revenue;
    private List<Contact> contacts;
    private Date lastActivity;

    // Just getters and setters...
    public String getTier() { return tier; }
    public void setTier(String tier) { this.tier = tier; }

    public BigDecimal getRevenue() {
        return null;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Date getLastActivity() {
        return lastActivity;
    }
    // ... 20 more getter/setter pairs
}
