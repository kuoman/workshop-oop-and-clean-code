package Workshop.Examples.Example2b;

import Workshop.Examples.Example2b.Other.*;

import java.time.Duration;

public class Account {
    private final AccountId id;
    private final CompanyName name;
    private AccountTier tier;
    private final Revenue revenue;
    private final ContactList contacts;
    private final ActivityTracker activityTracker;

    public Account(AccountId id, CompanyName name, Revenue revenue, ContactList contacts, ActivityTracker activityTracker) {
        this.id = id;
        this.name = name;
        this.revenue = revenue;
        this.contacts = contacts;
        this.activityTracker = activityTracker;
    }

    public boolean canUpgradeToEnterprise() {
        return revenue.isAtLeast(Money.of(100000)) &&
                contacts.hasMinimumOf(5) &&
                activityTracker.hasRecentActivity(Duration.ofDays(30));
    }

    public UpgradeResult upgradeToEnterprise() {
        if (!canUpgradeToEnterprise()) {
            return UpgradeResult.failed("Account doesn't meet enterprise criteria");
        }

        AccountTier previousTier = this.tier;
        this.tier = AccountTier.ENTERPRISE;

        return UpgradeResult.success(previousTier, tier);
    }

    public boolean isEnterprise() {
        return tier.isEnterprise();
    }
}
