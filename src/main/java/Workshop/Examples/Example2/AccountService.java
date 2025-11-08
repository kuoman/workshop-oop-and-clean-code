package Workshop.Examples.Example2;

import Workshop.Examples.Example2.Other.DateUtil;

import java.math.BigDecimal;

public class AccountService {
    public boolean canUpgradeToEnterprise(Account account) {
        return account.getRevenue().compareTo(new BigDecimal("100000")) >= 0 &&
                account.getContacts().size() >= 5 &&
                account.getLastActivity().after(DateUtil.thirtyDaysAgo());
    }

    public void upgradeToEnterprise(Account account) {
        if (canUpgradeToEnterprise(account)) {
            account.setTier("ENTERPRISE");
            // Send notification
            // Update metrics
            // Create tasks
        }
    }
}
