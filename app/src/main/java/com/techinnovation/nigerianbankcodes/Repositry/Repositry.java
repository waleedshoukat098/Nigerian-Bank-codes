package com.techinnovation.nigerianbankcodes.Repositry;

import com.techinnovation.nigerianbankcodes.Model.CodeModel;
import com.techinnovation.nigerianbankcodes.Model.DetailModel;
import com.techinnovation.nigerianbankcodes.R;

import java.util.ArrayList;

public class Repositry {

    public static ArrayList<DetailModel> getnetwork() {
        final ArrayList<DetailModel> listofnet = new ArrayList<>();
//        listofnet.add(new DetailModel(R.drawable., "Confirm Operator"));
        listofnet.add(new DetailModel(R.drawable.airtel, "Airtel"));
        listofnet.add(new DetailModel(R.drawable.nmobile, "9Mobile"));
        listofnet.add(new DetailModel(R.drawable.mtn, "MTN"));
        listofnet.add(new DetailModel(R.drawable.glo, "GLO"));

        return listofnet;
    }

    public static ArrayList<DetailModel> getbankdata() {
        final ArrayList<DetailModel> listofnet = new ArrayList<>();
        listofnet.add(new DetailModel(R.drawable.bnk1, "UBA"));
        listofnet.add(new DetailModel(R.drawable.bnk2, "Union"));
        listofnet.add(new DetailModel(R.drawable.bnk3, "Heritage"));
        listofnet.add(new DetailModel(R.drawable.bnk4, "Keystone"));
        listofnet.add(new DetailModel(R.drawable.bnk5, "Access"));
        listofnet.add(new DetailModel(R.drawable.bnk6, "Stanbic"));
        listofnet.add(new DetailModel(R.drawable.bnk7, "Zeenith"));
        listofnet.add(new DetailModel(R.drawable.banks, "Skye"));
        listofnet.add(new DetailModel(R.drawable.bnk9, "Sterling"));

        return listofnet;
    }

    public static ArrayList<CodeModel> swiftcode() {
        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("Access Bank plc-Lagos", "ABNGNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("11 PLC-Lagos", "MOOGNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("9 PAYMENT SERVICE BANK LIMITED-Lagos", "IPSBNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("ABSA SECURITIES NIGERIA LIMITED-Ikoyi", "", R.drawable.viber)); // Swift Code not provided
        listofnet.add(new CodeModel("ACCESS BANK PLC-Lagos", "ABNGNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("AFRICA FINANCE CORPORATION-Lagos", "AFFCNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("AFRICAN EXPORT-IMPORT BANK-Abuja", "PASSNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("ASSET MANAGEMENT CORPORATION OF NIGERIA-Abuja", "AMNGNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("BANK OF INDUSTRY FINANCIAL DEPARTMENT-Lagos", "", R.drawable.viber)); // Swift Code not provided
        listofnet.add(new CodeModel("BANK OF INDUSTRY LIMITED-Lagos", "NIDBNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("BRITISH AMERICAN TOBACCO (NIGERIA) LTD-Lagos", "BATSNG21", R.drawable.viber));
        listofnet.add(new CodeModel("BRITISH AMERICAN TOBACCO MARKETING NIGERIA LTD-Abuja", "BATSNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("CENTRAL BANK OF NIGERIA-Lagos", "CBNINGLA", R.drawable.viber));
        listofnet.add(new CodeModel("CENTRAL BANK OF NIGERIA-Lagos", "CBN FIN INFORMATION SERVICE", R.drawable.viber));
        listofnet.add(new CodeModel("CENTRAL SECURITIES CLEARING SYSTEM PLC-Lagos", "CSCYNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("CHAPEL HILL DENHAM SECURITIES LIMITED-Lagos", "CHDENGL1", R.drawable.viber));
        listofnet.add(new CodeModel("CITIBANK NIGERIA LIMITED-Lagos", "CITINGLA", R.drawable.viber));
        listofnet.add(new CodeModel("CITIBANK NIGERIA LIMITED-Lagos", "TRADE SERVICES DEPARTMENT", R.drawable.viber));
        listofnet.add(new CodeModel("CITY EXPRESS BANK LIMITED-Lagos", "CIEHNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("CORONATION MERCHANT BANK LIMITED-Lagos", "CMBBNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("CSL STOCKBROKERS LIMITED-Lagos", "CSSKNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("DEUTSCHE BANK REPRESENTATIVE OFFICE NIGERIA LIMITED-Lagos", "NIGINGL1", R.drawable.viber));
        listofnet.add(new CodeModel("DEVCOM BANK LTD-Lagos", "DEVCNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("ECOBANK NIGERIA LIMITED-Lagos", "ECOCNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("EFG HERMES NIGERIA LIMITED-Lagos", "EFHNNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("FBNQUEST MERCHANT BANK LIMITED-Lagos", "KDHLNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("FIDELITY BANK PLC-Lagos", "FIDTNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("FIDELITY BANK PLC-Lagos", "FUMRNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("FIDELITY BANK PLC-Lagos", "MAMBNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("FIRST BANK OF NIGERIA LTD-Lagos", "FBNINGLA", R.drawable.viber));
        listofnet.add(new CodeModel("FIRST BANK OF NIGERIA LTD-Lagos", "CUSTODIAN SERVICES", R.drawable.viber));
        listofnet.add(new CodeModel("FIRST CITY MONUMENT BANK LIMITED-Lagos", "FCMBNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("FIRST CITY MONUMENT BANK LIMITED-Lagos", "ELECTRONIC BANKING", R.drawable.viber));
        listofnet.add(new CodeModel("FIRST CITY MONUMENT BANK LTD-Kaduna", "KACBNG21", R.drawable.viber));
        listofnet.add(new CodeModel("FIRST CITY MONUMENT BANK LTD-Katsina", "KNUBNGL1022", R.drawable.viber));
        listofnet.add(new CodeModel("FIRST CITY MONUMENT BANK LTD-Lagos", "KNUBNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("FMDQ SECURITIES EXCHANGE LIMITED-Lagos", "FMDQNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("FORTUNE INTERNATIONAL BANC PLC-Lagos", "FOIBNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("FSDH MERCHANT BANK LTD-Lagos", "FSDHNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("GLOBUS BANK LIMITED-Lagos", "GLOUNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("GREENWICH MERCHANT BANK LIMITED-Lagos", "GMBLNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("GUARANTY TRUST BANK PLC-Lagos", "GTBINGLA", R.drawable.viber));
        listofnet.add(new CodeModel("GUARANTY TRUST BANK PLC-Lagos", "INFO POOL", R.drawable.viber));
        listofnet.add(new CodeModel("GULF BANK OF NIGERIA PLC-Lagos", "GBNPNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("HALLMARK BANK PLC-Lagos", "HALMNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("HERITAGE BANKING COMPANY LIMITED-Lagos", "HBCLNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("HOPE PSBANK-Lagos", "HPSBNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("INDORAMA ELEME FERTILIZER AND CHEMICALS LIMITED-Port Harcourt", "", R.drawable.viber)); // Swift Code not provided
        listofnet.add(new CodeModel("INDORAMA ELEME PETROCHEMICALS LIMITED-Port Harcourt", "INLHNGL1", R.drawable.viber));
        listofnet.add(new CodeModel("JAIZ BANK PLC-Abuja", "JAIZNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("KEYSTONE BANK LIMITED-Lagos", "PLNINGLA", R.drawable.viber));
        listofnet.add(new CodeModel("O3 CAPITAL LIMITED-Lagos", "", R.drawable.viber)); // Swift Code not provided
        listofnet.add(new CodeModel("OCEANIC BANK INTERNATIONAL PLC-Lagos", "OCEANGLAGOS", R.drawable.viber));
        listofnet.add(new CodeModel("PROVIDUS BANK LIMITED-Lagos", "PRVUNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("RAND MERCHANT BANK NIGERIA LIMITED-Lagos", "RMBLNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("RURAL ELECTRIFICATION AGENCY-Lagos", "", R.drawable.viber)); // Swift Code not provided
        listofnet.add(new CodeModel("SECURITIES AND EXCHANGE COMMISSION-Abuja", "", R.drawable.viber)); // Swift Code not provided
        listofnet.add(new CodeModel("SKYE BANK PLC-Lagos", "SIIBNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("SKYE BANK PLC-Lagos", "SIIBNGLAABJ", R.drawable.viber));
        listofnet.add(new CodeModel("STANBIC IBTC BANK PLC-Lagos", "SBICNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("STANBIC IBTC BANK PLC-Lagos", "SBICNGLXLPO", R.drawable.viber));
        listofnet.add(new CodeModel("STANBIC IBTC BANK PLC-Lagos", "SBICNGLXNOS", R.drawable.viber));
        listofnet.add(new CodeModel("STANDARD CHARTERED BANK NIGERIA LIMITED-Lagos", "SCBLNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("STERLING BANK PLC-Lagos", "NAMENGLA", R.drawable.viber));
        listofnet.add(new CodeModel("STERLING BANK PLC-Lagos", "STERLNB", R.drawable.viber));
        listofnet.add(new CodeModel("STERLING BANK PLC-Lagos", "STERLNBIBAD", R.drawable.viber));
        listofnet.add(new CodeModel("STERLING BANK PLC-Lagos", "STERLNBLAGOS", R.drawable.viber));
        listofnet.add(new CodeModel("UNITED BANK FOR AFRICA PLC-Lagos", "UNAFNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("UNITED BANK FOR AFRICA PLC-Lagos", "UNAFNGLXXXX", R.drawable.viber));
        listofnet.add(new CodeModel("UNITY BANK PLC-Lagos", "ICITNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("UNITY BANK PLC-Lagos", "UNTYNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("WEMA BANK PLC-Lagos", "WEMANGLA", R.drawable.viber));
        listofnet.add(new CodeModel("ZENITH BANK PLC-Lagos", "ZEIBNGLA", R.drawable.viber));
        listofnet.add(new CodeModel("ZENITH BANK PLC-Lagos", "ZEIBNGLAWEB", R.drawable.viber));
        return listofnet;
    }

    public static ArrayList<CodeModel> genral() {
        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("Emergency Number", "112", R.drawable.viber));

        // Verify Drivers License
        listofnet.add(new CodeModel("Verify Drivers License", "Text Drivers License Number to 33811", R.drawable.messages));

        // Report Domestic Violence & Child Abuse
        listofnet.add(new CodeModel("Report Domestic Violence & Child Abuse", "*6820#", R.drawable.viber));

        // Paga Service Code
        listofnet.add(new CodeModel("Paga Service Code", "*242#", R.drawable.viber));

        // Dstv Self Service
        listofnet.add(new CodeModel("Dstv Self Service", "*288#", R.drawable.viber));

        return listofnet;
    }

    public static ArrayList<CodeModel> Uba() {
        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("Check balance", "*919*00#", R.drawable.viber));
        listofnet.add(new CodeModel("Airtime for self", "*919*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Airtime for others", "*919*Phone number*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Buy data for self", "*919*14#", R.drawable.viber));
        listofnet.add(new CodeModel("Buy data for others", "*919*14*Phone number#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to UBA account", "*919*3*Account number*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to other accounts", "*919*4*Account Number", R.drawable.viber));
        listofnet.add(new CodeModel("UBA prepaid card", "*919*32#", R.drawable.viber));
        listofnet.add(new CodeModel("Pay bills", "*919*5#", R.drawable.viber));
        listofnet.add(new CodeModel("Block debit card", "*919*10#", R.drawable.viber));
        listofnet.add(new CodeModel("Flight payment", "*919*12#", R.drawable.viber));
        listofnet.add(new CodeModel("Open new UBA account", "*919*20#", R.drawable.viber));
        listofnet.add(new CodeModel("Freeze online transactions", "*919*9#", R.drawable.viber));
        listofnet.add(new CodeModel("Retrieve BVN", "*919*18#", R.drawable.viber));
        listofnet.add(new CodeModel("ATM cardless withdrawals", "*919*30*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Get Bank statements", "*919*21#", R.drawable.viber));
        listofnet.add(new CodeModel("Generate OTP", "*919*8#", R.drawable.viber));
        listofnet.add(new CodeModel("Smile data top-up", "*919*23#", R.drawable.viber));
        listofnet.add(new CodeModel("Self-enrollment registration", "*919*0#", R.drawable.viber));
        listofnet.add(new CodeModel("Bet9ja wallet funding", "*919*22*walletID*amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Baba Ijebu wallet funding", "*919*26*1#", R.drawable.viber));
        listofnet.add(new CodeModel("Betking wallet funding", "*919*26*amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Sportybet wallet funding", "", R.drawable.viber)); // Replace with the appropriate USSD code
        listofnet.add(new CodeModel("Lottomania wallet funding", "*919*26*2#", R.drawable.viber));
        listofnet.add(new CodeModel("Oak pensions", "*919*27*2#", R.drawable.viber));
        listofnet.add(new CodeModel("ARM pensions", "*919*27*1#", R.drawable.viber));
        listofnet.add(new CodeModel("Purchase event tickets", "*919*7#", R.drawable.viber));
        listofnet.add(new CodeModel("Taxes and Levies", "*919*13#", R.drawable.viber));
        listofnet.add(new CodeModel("Check LLC e-tag balance", "*919*16*1#", R.drawable.viber));
        listofnet.add(new CodeModel("Top up LLC e-tag", "*919*16*2#", R.drawable.viber));
        listofnet.add(new CodeModel("DStv and Gotv payment", "*919*5*2#", R.drawable.viber));
        listofnet.add(new CodeModel("Konga payment", "*919*15*RefID#", R.drawable.viber));

        return listofnet;
    }

    public static ArrayList<CodeModel> Union() {
        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("Transfer money to the account", "*826*1*Amount*Account No#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to other banks", "*826*2*Amount*Account no#", R.drawable.viber));
        listofnet.add(new CodeModel("Check account balance", "*826*4#", R.drawable.viber));
        listofnet.add(new CodeModel("Request for loan", "*826*41#", R.drawable.viber));
        listofnet.add(new CodeModel("Buy airtime", "*826*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Buy airtime for others", "*826*Amount*3rd party mobile Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Purchase data", "*826*9#", R.drawable.viber));
        listofnet.add(new CodeModel("Data capture after enrollment", "*826*3*account number#", R.drawable.viber));
        listofnet.add(new CodeModel("To block an account on your phone number", "*826*6#", R.drawable.viber));
        listofnet.add(new CodeModel("To block accounts on another person’s phone line", "*826*6*mobile number#", R.drawable.viber));
        listofnet.add(new CodeModel("Pay merchant (mCash)", "*826*22*merchantcode*amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Request for ATM card", "*826*21#", R.drawable.viber));
        listofnet.add(new CodeModel("Cardless withdrawal", "*826*7*amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Card management (Block or Unblock)", "*826*21#", R.drawable.viber));
        listofnet.add(new CodeModel("Limit increase", "*826*8#", R.drawable.viber));
        listofnet.add(new CodeModel("Locate Union direct agent", "*826*19#", R.drawable.viber));

        return listofnet;

    }

    public static ArrayList<CodeModel> Heritage() {
        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("Balance Enquiry", "*745*0#", R.drawable.viber));
        listofnet.add(new CodeModel("Funds Transfer (Intra)", "*745*1*Amount*Account Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Funds Transfer (InterBank)", "*745*2*Amount*Account Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Self-Airtime Recharge", "*745*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Third Party Airtime Recharge", "*745*Amount*Mobile Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Change USSD PIN", "*745*00#", R.drawable.viber));
        listofnet.add(new CodeModel("Bet9ja Top-up", "*745*222*Bet9jaUserID*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Pay with USSD (on POS & Web)", "*745*000*RefCode#", R.drawable.viber));
        listofnet.add(new CodeModel("LCC Payment", "*745*000*522+etagNo+100#", R.drawable.viber));
        listofnet.add(new CodeModel("Block Account from any phone", "*745*11#", R.drawable.viber));

        return listofnet;

    }

    public static ArrayList<CodeModel> KeyStone() {
        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("Airtime Top-Up for Others", "*770*PHONE*AMOUNT#", R.drawable.viber));
        listofnet.add(new CodeModel("Airtime Top-Up for Self", "*770*AMOUNT#", R.drawable.viber));
        listofnet.add(new CodeModel("ATM Cardless Withdrawal", "*770*8*AMOUNT#", R.drawable.viber));
        listofnet.add(new CodeModel("Block Account from USSD Banking", "*770*08012345678#", R.drawable.viber));
        listofnet.add(new CodeModel("Block Card", "*770*911#", R.drawable.viber));
        listofnet.add(new CodeModel("Block Phone from USSD Banking", "*770*911*08012345678#", R.drawable.viber));
        listofnet.add(new CodeModel("Change PIN", "*770*00#", R.drawable.viber));
        listofnet.add(new CodeModel("Check Balance", "*770*0#", R.drawable.viber));
        listofnet.add(new CodeModel("Choose SMS and Email Alert", "*770*2#", R.drawable.viber));
        listofnet.add(new CodeModel("Pay Bills", "*770#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to Fidelity Account", "*770*ACCOUNT*AMOUNT#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to Other Banks", "*770*ACCOUNT*AMOUNT#", R.drawable.viber));
        listofnet.add(new CodeModel("Update BVN", "*770*02#", R.drawable.viber));
        listofnet.add(new CodeModel("Locate Union Direct Agent", "*826*19#", R.drawable.viber));
        return listofnet;
    }


    public static ArrayList<CodeModel> Access() {
        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("Dialing Code", "*901#", R.drawable.viber));
        listofnet.add(new CodeModel("Account Opening", "*901*0#", R.drawable.viber));
        listofnet.add(new CodeModel("Airtime for Self", "*901*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Data Purchase", "*901*8#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to Access Bank Account", "*901*1*Amount*Account Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to Other Banks", "*901*2*Amount*Account Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Check Balance", "*901*5#", R.drawable.viber));
        listofnet.add(new CodeModel("Pay Bills", "*901*3#", R.drawable.viber));
        listofnet.add(new CodeModel("Airtime Top Up Others", "*901*Amount*Phone Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Generate OTP", "*901*4*1#", R.drawable.viber));
        listofnet.add(new CodeModel("Payday Loan", "*901*11#", R.drawable.viber));
        listofnet.add(new CodeModel("BVN Retrieval", "*565*0#", R.drawable.viber));
        listofnet.add(new CodeModel("Deactivate Account", "*901*0#", R.drawable.viber));
        listofnet.add(new CodeModel("Block ATM Card", "*901*11#", R.drawable.viber));


        return listofnet;
    }

    public static ArrayList<CodeModel> Stanbic() {

        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("Change PIN", "*909*11*1#", R.drawable.viber));
        listofnet.add(new CodeModel("Check Balance", "*909#", R.drawable.viber));
        listofnet.add(new CodeModel("Recharge Airtime", "*909*amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Mini Statement", "*909*20#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer Funds", "*909*22#", R.drawable.viber));

        listofnet.add(new CodeModel("USSD Card", "*909#", R.drawable.viber));
        listofnet.add(new CodeModel("Start", "*966#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to Zenith Bank", "*966*Amount*Account Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to Other Banks", "*966*Amount*Account Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Airtime for Self", "*966*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Airtime for Others", "*966*Amount*Mobile Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Check Balance", "*966*00#", R.drawable.viber));
        listofnet.add(new CodeModel("BVN", "*565*0#", R.drawable.viber));
        listofnet.add(new CodeModel("Update BVN Details", "*966*11 Digit BVN#", R.drawable.viber));
        listofnet.add(new CodeModel("Stop Debit Transactions", "*966*911#", R.drawable.viber));
        listofnet.add(new CodeModel("Activate Code", "*966*00#", R.drawable.viber));


        return listofnet;
    }

    public static ArrayList<CodeModel> Zeenith() {

        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("USSD Card", "*909#", R.drawable.viber));
        listofnet.add(new CodeModel("Start", "*966#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to Zenith Bank", "*966*Amount*Account Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer to Other Banks", "*966*Amount*Account Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Airtime for Self", "*966*Amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Airtime for Others", "*966*Amount*Mobile Number#", R.drawable.viber));
        listofnet.add(new CodeModel("Check Balance", "*966*00#", R.drawable.viber));
        listofnet.add(new CodeModel("BVN", "*565*0#", R.drawable.viber));
        listofnet.add(new CodeModel("Update BVN Details", "*966*11 Digit BVN#", R.drawable.viber));
        listofnet.add(new CodeModel("Stop Debit Transactions", "*966*911#", R.drawable.viber));
        listofnet.add(new CodeModel("Activate Code", "*966*00#", R.drawable.viber));
        return listofnet;
    }

    public static ArrayList<CodeModel> Skye() {

        final ArrayList<CodeModel> listofnet = new ArrayList<>();

        listofnet.add(new CodeModel("Check Balance", "*966*00#", R.drawable.viber));

        return listofnet;

    }


    public static ArrayList<CodeModel> Sterling() {

        final ArrayList<CodeModel> listofnet = new ArrayList<>();


        listofnet.add(new CodeModel("Recharge Airtime (Self)", "*822*amount#", R.drawable.viber));
        listofnet.add(new CodeModel("Open New Sterling Bank Account", "*822*7#", R.drawable.viber));
        listofnet.add(new CodeModel("Pay Bills with Sterling Bank", "*822*2#", R.drawable.viber));
        listofnet.add(new CodeModel("Buy Airtime for Another Phone Number", "*822*amount*phone number#", R.drawable.viber));
        listofnet.add(new CodeModel("Transfer Money to Any Bank", "*822*amount*account number#", R.drawable.viber));
        listofnet.add(new CodeModel("Manage Your Cards with Sterling Bank", "*822*19#", R.drawable.viber));
        listofnet.add(new CodeModel("Card-less Withdrawal Code", "*822*42#", R.drawable.viber));
        listofnet.add(new CodeModel("Check Balance/BVN/Account Number", "*822*6#", R.drawable.viber));
        return listofnet;
    }

    public static ArrayList<CodeModel> Airtel() {

        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        listofnet.add(new CodeModel("Airtime Recharge", "*311*Voucher PIN#", R.drawable.viber));

        // Check Airtel Airtime Balance
        listofnet.add(new CodeModel("Check Airtel Airtime Balance", "*310#", R.drawable.viber));

        // Borrow Airtime
        listofnet.add(new CodeModel("Borrow Airtime", "*303#", R.drawable.viber));

        // Buy Data
        listofnet.add(new CodeModel("Buy Data", "*312#", R.drawable.viber));

        // Share Data
        listofnet.add(new CodeModel("Share Data", "*321#", R.drawable.viber));

        // Check Data Balance
        listofnet.add(new CodeModel("Check Data Balance", "*323#", R.drawable.viber));

        // Airtel Value-added Services
        listofnet.add(new CodeModel("Airtel Value-added Services", "*305#", R.drawable.viber));

        // Link NIN to Airtel Line
        listofnet.add(new CodeModel("Link NIN to Airtel Line", "*996#", R.drawable.viber));

        listofnet.add(new CodeModel("To Recharge", "*126*PIN NO#", R.drawable.viber));
        listofnet.add(new CodeModel("To Check Balance", "*123#", R.drawable.viber));

        // INTERNATIONAL BUNDLES
        listofnet.add(new CodeModel("N10,000 Bundle", "*789*110#", R.drawable.viber));
        listofnet.add(new CodeModel("N5,000 Bundle", "*789*550#", R.drawable.viber));
        listofnet.add(new CodeModel("N1,000 Bundle", "*789*100#", R.drawable.viber));
        listofnet.add(new CodeModel("N500 Bundle", "*789*50#", R.drawable.viber));
        listofnet.add(new CodeModel("N200 Bundle", "*789*20#", R.drawable.viber));
        listofnet.add(new CodeModel("N100 Bundle", "*789*10#", R.drawable.viber));

        // INTERNATIONAL BUNDLE AMOUNT
        listofnet.add(new CodeModel("N500 Bundle", "*789*75#", R.drawable.viber));
        listofnet.add(new CodeModel("N200 Bundle", "*789*72#", R.drawable.viber));

        // SMARTSPEEDOO DATA PLANS
        listofnet.add(new CodeModel("No Commitment", "*400#", R.drawable.viber));
        listofnet.add(new CodeModel("Free Surf", "*410#", R.drawable.viber));
        listofnet.add(new CodeModel("3-Day Bundle", "*412#", R.drawable.viber));
        listofnet.add(new CodeModel("5-Star Pack", "*401#", R.drawable.viber));
        listofnet.add(new CodeModel("Weekly Bundle", "*417#", R.drawable.viber));
        listofnet.add(new CodeModel("Easy Bundle", "*418#", R.drawable.viber));
        listofnet.add(new CodeModel("2G Pack Unlimited", "*482*1#", R.drawable.viber));
        listofnet.add(new CodeModel("2G Pack Unlimited", "*482*2#", R.drawable.viber));

        // ANDROID DATA PLANS
        listofnet.add(new CodeModel("Android 1.0", "*496#", R.drawable.viber));
        listofnet.add(new CodeModel("Android 2.0", "*437#", R.drawable.viber));
        listofnet.add(new CodeModel("Android 2.5", "*437*1#", R.drawable.viber));
        listofnet.add(new CodeModel("Android 3.5", "*438#", R.drawable.viber));
        listofnet.add(new CodeModel("Android 4.0", "*438*1#", R.drawable.viber));
        listofnet.add(new CodeModel("Android 1.0", "*496#", R.drawable.viber));
        listofnet.add(new CodeModel("Android 2.0", "*437#", R.drawable.viber));
        listofnet.add(new CodeModel("Android 2.5", "*437*1#", R.drawable.viber));

        // SOCIAL BUNDLES
        listofnet.add(new CodeModel("Opera Mini – Large", "*885*1#", R.drawable.viber));
        listofnet.add(new CodeModel("WTF_Bundle_Large", "*990#", R.drawable.viber));
        listofnet.add(new CodeModel("UNLIMITED MONTH", "*435#", R.drawable.viber));
        listofnet.add(new CodeModel("UNLIMITED WEEK", "*440*17#", R.drawable.viber));
        listofnet.add(new CodeModel("UNLIMITED DAY", "*440*18#", R.drawable.viber));

        // MEGA PACKS
        listofnet.add(new CodeModel("MEGA 5", "*452#", R.drawable.viber));
        listofnet.add(new CodeModel("MEGA 8", "*460#", R.drawable.viber));
        listofnet.add(new CodeModel("MEGA 10", "*462#", R.drawable.viber));
        listofnet.add(new CodeModel("MEGA 15", "*463#", R.drawable.viber));
        listofnet.add(new CodeModel("MEGA 36", "*406#", R.drawable.viber));
        listofnet.add(new CodeModel("MEGA 70", "*407#", R.drawable.viber));
        listofnet.add(new CodeModel("MEGA 136", "*408#", R.drawable.viber));
        listofnet.add(new CodeModel("MEGA 5", "*452#", R.drawable.viber));
        listofnet.add(new CodeModel("UNLIMINET Daily", "*489*1#", R.drawable.viber));
        listofnet.add(new CodeModel("UNLIMINET Monthly", "*489*2#", R.drawable.viber));
        listofnet.add(new CodeModel("UNLIMINET Monthly_I", "*489*3#", R.drawable.viber));
        listofnet.add(new CodeModel("UNLIMINET Monthly_II", "*489*4#", R.drawable.viber));

        // TRIPLE SURF OFFER
        listofnet.add(new CodeModel("Triple Surf Offer Daily", "*141#", R.drawable.viber));
        listofnet.add(new CodeModel("Triple Surf Offer 3-Day", "*141#", R.drawable.viber));
        listofnet.add(new CodeModel("Triple Surf Offer Weekly", "*141#", R.drawable.viber));
        listofnet.add(new CodeModel("Triple Surf Offer Easy", "*141#", R.drawable.viber));
        listofnet.add(new CodeModel("Triple Surf Offer Android1", "*141#", R.drawable.viber));
        listofnet.add(new CodeModel("Triple Surf Offer Android2", "*141#", R.drawable.viber));
        listofnet.add(new CodeModel("Triple Surf Offer Android3.5", "*141#", R.drawable.viber));

        return  listofnet;

    }

    public static ArrayList<CodeModel> NMObile() {

        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        // Contact 9mobile Customer Service
        listofnet.add(new CodeModel("Contact 9mobile Customer Service", "300", R.drawable.viber));

        // Check Airtime Balance
        listofnet.add(new CodeModel("Check Airtime Balance", "*310#", R.drawable.viber));

        // Check Data Balance
        listofnet.add(new CodeModel("Check Data Balance", "*323#", R.drawable.viber));

        // Load Recharge Card
        listofnet.add(new CodeModel("Load Recharge Card", "*311*PIN#", R.drawable.viber));

        // Access Data Plans
        listofnet.add(new CodeModel("Access Data Plans", "*312*1#", R.drawable.viber));

        // Voicemail Services
        listofnet.add(new CodeModel("Voicemail Services", "302", R.drawable.viber));

        // MoreCredit Service
        listofnet.add(new CodeModel("MoreCredit Service", "*303#", R.drawable.viber));

        return listofnet;

    }




    public static ArrayList<CodeModel> MTN() {

        final ArrayList<CodeModel> listofnet = new ArrayList<>();
        // MTN Airtime Recharge
        listofnet.add(new CodeModel("Airtime Recharge", "*311*Voucher PIN#", R.drawable.viber));

        // Check MTN Airtime Balance
        listofnet.add(new CodeModel("Check Airtime Balance", "*310#", R.drawable.viber));

        // Borrow Airtime
        listofnet.add(new CodeModel("Borrow Airtime", "*303#", R.drawable.viber));

        // Buy Data
        listofnet.add(new CodeModel("Buy Data", "*312#", R.drawable.viber));

        // Share Data
        listofnet.add(new CodeModel("Share Data", "*321#", R.drawable.viber));

        // Check Data Balance
        listofnet.add(new CodeModel("Check Data Balance", "*323#", R.drawable.viber));

        // MTN Value-added Services
        listofnet.add(new CodeModel("MTN Value-added Services", "*305#", R.drawable.viber));

        // Link NIN to MTN Line
        listofnet.add(new CodeModel("Link NIN to MTN Line", "*996#", R.drawable.viber));

        // Check MTN Phone Number
        listofnet.add(new CodeModel("Check Phone Number", "*123#", R.drawable.viber));

        // Transfer Airtime
        listofnet.add(new CodeModel("Transfer Airtime", "*321*Default PIN*New PIN*New PIN#", R.drawable.viber));


        // DAILY PLANS
        listofnet.add(new CodeModel("30MB Daily", "104", R.drawable.viber));
        listofnet.add(new CodeModel("100MB Daily", "113", R.drawable.viber));

        // WEEKLY PLANS
        listofnet.add(new CodeModel("750MB Weekly", "103", R.drawable.viber));

        // MONTHLY PLANS
        listofnet.add(new CodeModel("1.5 GB Monthly", "106", R.drawable.viber));
        listofnet.add(new CodeModel("3.5 GB Monthly", "110", R.drawable.viber));
        listofnet.add(new CodeModel("10 GB Monthly", "116", R.drawable.viber));
        listofnet.add(new CodeModel("22 GB Monthly", "117", R.drawable.viber));

        // 60 DAYS PLANS
        listofnet.add(new CodeModel("50 GB 60 Days", "118", R.drawable.viber));

        // QUARTERLY PLANS
        listofnet.add(new CodeModel("85 GB 90 Days", "133", R.drawable.viber));

        // MTN GOODYBAG SOCIAL
        listofnet.add(new CodeModel("Goody Bag Daily - WhatsApp", "*662#", R.drawable.viber));
        listofnet.add(new CodeModel("Goody Bag Daily - Facebook", "FBD", R.drawable.viber));
        listofnet.add(new CodeModel("Goody Bag Daily - Twitter", "TWTD", R.drawable.viber));
        listofnet.add(new CodeModel("Goody Bag Daily - 2Go", "2GOD", R.drawable.viber));
        listofnet.add(new CodeModel("Goody Bag Daily - WeChat", "WCD", R.drawable.viber));
        listofnet.add(new CodeModel("Goody Bag Daily - Eskimi", "ESKD", R.drawable.viber));
        listofnet.add(new CodeModel("Goody Bag Daily - Nimbuzz", "NIMD", R.drawable.viber));

        // Goody Bag Weekly
        listofnet.add(new CodeModel("Goody Bag Weekly - WhatsApp", "WAW", R.drawable.viber));listofnet.add(new CodeModel("Check Balance", "*966*00#", R.drawable.viber));

        return listofnet;

    }




    public static ArrayList<CodeModel> GLO() {

        final ArrayList<CodeModel> listofnet = new ArrayList<>();

        listofnet.add(new CodeModel("Contact GLO Customer Care", "300", R.drawable.viber));

        // Check Glo Data Balance
        listofnet.add(new CodeModel("Check Glo Data Balance", "*312*0#", R.drawable.viber));

        // Check Airtime Balance
        listofnet.add(new CodeModel("Check Airtime Balance", "*310#", R.drawable.viber));

        // Check Voice Message Length
        listofnet.add(new CodeModel("Check Voice Message Length", "*312*1#", R.drawable.viber));

        // Transfer or Share Glo Data
        listofnet.add(new CodeModel("Transfer or Share Glo Data", "*321#", R.drawable.viber));

        // Share Glo Airtime
        listofnet.add(new CodeModel("Share Glo Airtime", "*312*receiver phone number*amount*your transfer pin#", R.drawable.viber));

        // Create Glo Share/Transfer PIN
        listofnet.add(new CodeModel("Create Glo Share/Transfer PIN", "*132*00000*New PIN*New PIN#", R.drawable.viber));

        // Check SMS Balance
        listofnet.add(new CodeModel("Check SMS Balance", "*311*1#", R.drawable.viber));

        // Borrow Airtime from GLO
        listofnet.add(new CodeModel("Borrow Airtime from GLO", "*303#", R.drawable.viber));

        // Borrow Data from GLO
        listofnet.add(new CodeModel("Borrow Data from GLO", "*303#", R.drawable.viber));

        // Borrow For Others
        listofnet.add(new CodeModel("Borrow For Others", "*303#", R.drawable.viber));

        // Recharge GLO Card
        listofnet.add(new CodeModel("Recharge GLO Card", "*311*PIN#", R.drawable.viber));

        // Migrate to GLO Friends & Family
        listofnet.add(new CodeModel("Migrate to GLO Friends & Family", "*100*9*1# or *100*9*2#", R.drawable.viber));

        // Add 10 frequently called family and friends
        listofnet.add(new CodeModel("Add 10 frequently called family and friends", "*101*1*Mobile No#", R.drawable.viber));

        // Buy GLO Data
        listofnet.add(new CodeModel("Buy GLO Data", "*312#", R.drawable.viber));

        // Check GLO Mobile Number
        listofnet.add(new CodeModel("Check GLO Mobile Number", "*135*8# or Dial 1244", R.drawable.viber));

        // GLO Data Gifting
        listofnet.add(new CodeModel("GLO Data Gifting", "*312# or Send 'Gift [friend’s number]' to 312", R.drawable.viber));

        return listofnet;

    }
    public static ArrayList<CodeModel> Generals() {

        final ArrayList<CodeModel> listofnet = new ArrayList<>();

        return  listofnet;

    }
}


