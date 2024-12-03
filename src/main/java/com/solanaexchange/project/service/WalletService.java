package com.solanaexchange.project.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
public class WalletService {
    private static final String FUND = "FUND";
    private static final String SPOT = "SPOT";
    public List<String> generateWalletNumbers(String email){
        int i = email.indexOf("@");
        String name = email.substring(0,i);
        Calendar c = Calendar.getInstance();
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String year = String.valueOf(c.get(Calendar.YEAR));
        String hour = String.valueOf(c.get(Calendar.HOUR));
        String min = String.valueOf(c.get(Calendar.MINUTE));
        String sec = String.valueOf(c.get(Calendar.SECOND));

        List<String> walletNumbers = new ArrayList<>();
        String fundWalletNumber = name + FUND + day+ month +year + hour+min+sec;
        String spotWalletNumber = name + SPOT + day+ month +year + hour+min+sec;
        walletNumbers.add(fundWalletNumber);
        walletNumbers.add(spotWalletNumber);
        return walletNumbers;

    }
}
