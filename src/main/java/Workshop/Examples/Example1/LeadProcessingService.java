package Workshop.Examples.Example1;

import Workshop.Examples.Example1.Other.*;

import java.math.BigDecimal;

public class LeadProcessingService {

    private LeadRepository leadRepo;
    private AccountRepository accountRepo;
    private OpportunityRepository oppRepo;
    private EmailService emailService;
    private ValidationService validationService;
    private AuditService auditService;
    private MetricsCollector metrics;

    // What makes this hard to work with?
    // Let's identify the problems together.

    public void processLead(LeadData leadData) throws ValidationException {
        // Validation (40 lines)
        if (leadData.getEmail() == null || !leadData.getEmail().contains("@")) {
            throw new ValidationException("Invalid email");
        }
        if (leadData.getCompany() == null || leadData.getCompany().trim().isEmpty()) {
            throw new ValidationException("Company required");
        }
        // ... more validation

        // Business rules (60 lines)
        Lead lead = new Lead();
        lead.setEmail(leadData.getEmail());
        lead.setCompany(leadData.getCompany());
        lead.setStatus("NEW");

        // Check for existing account
        Account existingAccount = accountRepo.findByName(leadData.getCompany());
        if (existingAccount != null) {
            lead.setAccount(existingAccount);
            // Existing customer logic
            if (existingAccount.getTier().equals("ENTERPRISE")) {
                lead.setPriority("HIGH");
                emailService.notifyEnterpriseTeam(lead);
            }
        } else {
            // New customer logic
            lead.setPriority("MEDIUM");
        }

        // Data persistence (20 lines)
        lead = leadRepo.save(lead);

        // Side effects (30 lines)
        auditService.logLeadCreation(lead);
        metrics.incrementCounter("leads.created");
        emailService.sendWelcomeEmail(lead);

        // More business logic mixed in...
        if (lead.getSource().equals("WEBINAR") &&
                lead.getAccount() != null &&
                lead.getAccount().getTier().equals("ENTERPRISE")) {

            Opportunity opp = new Opportunity();
            opp.setAccount(lead.getAccount());
            opp.setAmount(new BigDecimal("50000"));
            opp.setStage("PROSPECTING");
            oppRepo.save(opp);

            emailService.notifyAccountManager(opp);
        }
    }
}
