package Workshop.Examples.Example2b;

import Workshop.Examples.Example2b.Other.*;

public class AccountService {
    private final AccountRepository repository;
    private final UpgradeNotifier notifier;

    public AccountService(AccountRepository repository, UpgradeNotifier notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    public void processUpgrade(AccountId accountId) {
        Account account = repository.findById(accountId);
        UpgradeResult result = account.upgradeToEnterprise();

        if (result.isSuccess()) {
            repository.save(account);
            notifier.notifyUpgrade(account, result);
        }
    }
}
