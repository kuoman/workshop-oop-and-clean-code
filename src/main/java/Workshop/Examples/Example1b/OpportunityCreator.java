package Workshop.Examples.Example1b;

import Workshop.Examples.Example1b.Other.*;

public class OpportunityCreator {
    private final OpportunityRepository repository;
    private final OpportunityNotifier notifier;

    public OpportunityCreator(OpportunityRepository repository, OpportunityNotifier notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    public void createFor(Lead lead) {
        Opportunity opportunity = Opportunity.fromLead(lead);
        repository.save(opportunity);
        notifier.notifyAccountManager(opportunity);
    }
}
