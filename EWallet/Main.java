package EWallet;

import EWallet.Services.ApplicationService;
import EWallet.Services.Imp.ApplicationServiceImp;
import EWallet.V1.WalletSys;

public class Main {

    public static void main(String[] args) {

        new ApplicationServiceImp().start();

    }
}