package EWallet.Services;

import EWallet.V1.Account;

public interface TransferService {

    void transfer(Account sender);
}