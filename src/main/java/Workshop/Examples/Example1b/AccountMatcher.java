package Workshop.Examples.Example1b;

import Workshop.Examples.Example1b.Other.*;

public class AccountMatcher {
    private final AccountRepository accountRepository;

    public AccountMatcher(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findFor(Lead lead) {
        return accountRepository.findByCompanyName(lead.getCompanyName());
    }
}
