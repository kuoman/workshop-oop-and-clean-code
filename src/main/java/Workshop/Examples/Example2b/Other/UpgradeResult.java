package Workshop.Examples.Example2b.Other;

public class UpgradeResult {
    public static UpgradeResult failed(String s) {
        return null;
    }

    public static UpgradeResult success(AccountTier previousTier, AccountTier tier) {
        return null;
    }

    public boolean isSuccess() {
        return false;
    }
}
