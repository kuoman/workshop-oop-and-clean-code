package Workshop.Examples.Example1b;

import Workshop.Examples.Example1b.Other.*;

public class LeadProcessingService {

    private final LeadValidator validator;
    private final AccountMatcher accountMatcher;
    private final LeadRepository leadRepository;
    private final OpportunityCreator opportunityCreator;
    private final LeadNotifier notifier;

    public LeadProcessingService(LeadValidator validator, AccountMatcher accountMatcher, LeadRepository leadRepository, OpportunityCreator opportunityCreator, LeadNotifier notifier) {
        this.validator = validator;
        this.accountMatcher = accountMatcher;
        this.leadRepository = leadRepository;
        this.opportunityCreator = opportunityCreator;
        this.notifier = notifier;
    }

    public void processLead(LeadData leadData) {
        validator.validate(leadData);

        Lead lead = new Lead(leadData.getEmail(), leadData.getCompany());

        Account matchingAccount = accountMatcher.findFor(lead);
        if (matchingAccount != null) {
            lead.assignToAccount(matchingAccount);
        }

        lead = leadRepository.save(lead);

        notifier.notifyCreated(lead);

        if (lead.isQualifiedForAutoOpportunity()) {
            opportunityCreator.createFor(lead);
        }
    }
}
