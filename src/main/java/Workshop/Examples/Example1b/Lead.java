package Workshop.Examples.Example1b;

import Workshop.Examples.Example1b.Other.*;

public class Lead {
    private final Email email;
    private final Company company;
    private LeadStatus status;
    private Account account;
    private Priority priority;
    private Source source;

    public Lead(Email email, Company company) {
        this.email = email;
        this.company = company;
        this.status = LeadStatus.NEW;
        this.priority = Priority.MEDIUM;
    }

    public void assignToAccount(Account account) {
        this.account = account;
        if (account.isEnterprise()) {
            this.priority = Priority.HIGH;
        }
    }

    public boolean isQualifiedForAutoOpportunity() {
        return hasWebinarSource() && hasEnterpriseAccount();
    }

    private boolean hasWebinarSource() {
        return source.equals(Source.WEBINAR);
    }

    private boolean hasEnterpriseAccount() {
        return account != null && account.isEnterprise();
    }

    public String getCompanyName() {
        return null;
    }
}
