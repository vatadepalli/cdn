// ********************  POJO 1.1 ********************

package com.app.core;

import java.io.Serializable;
import java.time.LocalDate;

public class BillingStructure implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private LocalDate billDate;
    private double amount;
    private String phoneNo;

    public BillingStructure(LocalDate billDate, double amount, String phoneNo) {
        super();
        this.billDate = billDate;
        this.amount = amount;
        this.phoneNo = phoneNo;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "BillingStructure [billDate=" + billDate + ", amount=" + amount + ", phoneNo=" + phoneNo + "]";
    }

}

// ******************** POJO 1.2 ********************

package com.app.core;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

public class PhoneBill implements Serializable {

    private static final long serialVersionUID = 1L;
    private String phoneNo;
    private String name;
    private LocalDate dob;
    private HashMap<Month, BillingStructure> monthlyBills;

    public PhoneBill(String phoneNo, String name, LocalDate dob) {
        super();
        this.phoneNo = phoneNo;
        this.name = name;
        this.dob = dob;
        monthlyBills = new HashMap<>();
    }

    public void addBills(LocalDate dt, double billAmt) {
        monthlyBills.put(dt.getMonth(), new BillingStructure(dt, billAmt, phoneNo));
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public HashMap<Month, BillingStructure> getMonthlyBills() {
        return monthlyBills;
    }

    public void setMonthlyBills(HashMap<Month, BillingStructure> monthlyBills) {
        this.monthlyBills = monthlyBills;
    }

    @Override
    public String toString() {
        return "PhoneBill [phoneNo=" + phoneNo + ", name=" + name + ", dob=" + dob + ", monthlyBills=" + monthlyBills
                + "]";
    }

}

// ******************** UTILS 1.1 - Colletion Utils ********************

package utils;

import java.util.HashMap;
import static java.time.LocalDate.*;
import com.app.core.PhoneBill;

public class CollectionUtils {

    public static HashMap<String, PhoneBill> populateData() {
        HashMap<String, PhoneBill> hm = new HashMap<String, PhoneBill>();
        hm.put("8319111641", new PhoneBill("8319111641", "Akash", parse("1993-09-03")));
        hm.get("8319111641").addBills(parse("2019-07-01"), 1000);
        hm.get("8319111641").addBills(parse("2019-08-01"), 1010);
        hm.get("8319111641").addBills(parse("2019-09-01"), 1200);
        hm.get("8319111641").addBills(parse("2019-10-01"), 2100);
        hm.get("8319111641").addBills(parse("2019-11-01"), 1000);
        hm.get("8319111641").addBills(parse("2019-12-01"), 3200);

        hm.put("9876543021", new PhoneBill("9876543021", "Pankaj", parse("1996-08-19")));
        hm.get("9876543021").addBills(parse("2019-07-01"), 1200);
        hm.get("9876543021").addBills(parse("2019-08-01"), 1110);
        hm.get("9876543021").addBills(parse("2019-09-01"), 1500);
        hm.get("9876543021").addBills(parse("2019-10-01"), 2200);
        hm.get("9876543021").addBills(parse("2019-11-01"), 900);
        hm.get("9876543021").addBills(parse("2019-12-01"), 3900);

        hm.put("7564839201", new PhoneBill("7564839201", "Saurabh", parse("1994-12-03")));
        hm.get("7564839201").addBills(parse("2019-07-01"), 1020);
        hm.get("7564839201").addBills(parse("2019-08-01"), 1210);
        hm.get("7564839201").addBills(parse("2019-09-01"), 1400);
        hm.get("7564839201").addBills(parse("2019-10-01"), 2000);
        hm.get("7564839201").addBills(parse("2019-11-01"), 1450);
        hm.get("7564839201").addBills(parse("2019-12-01"), 4800);

        return hm;
    }

}

// ******************** UTILS 1.2 - IOUtils ********************

package utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.app.core.BillingStructure;
import com.app.core.PhoneBill;

import static java.time.LocalDate.*;

public class IOUtils {

    public static void sendCurrentMonthBill(ObjectOutputStream out, HashMap<Month, BillingStructure> bills)
            throws IOException {
        if (bills.containsKey(now().getMonth()))
            out.writeObject(bills.get(now().getMonth()));
    }

    public static void sendSixMonthsBill(ObjectOutputStream out, HashMap<Month, BillingStructure> bills)
            throws IOException {
        out.writeObject(bills);
    }

    public static void maxPhoneBill(ObjectOutputStream out, HashMap<String, PhoneBill> hm) throws IOException
	{
		List<BillingStructure> maxBills=new ArrayList<>();
		for(PhoneBill pb: hm.values())
		{
			BillingStructure bills=Collections.max(pb.getMonthlyBills().values(), new Comparator<BillingStructure>() {

				@Override
				public int compare(BillingStructure o1, BillingStructure o2) {
					return ((Double)o1.getAmount()).compareTo(o2.getAmount());
				}
				
			});
			maxBills.add(bills);
		}
		BillingStructure max = Collections.max(maxBills, new Comparator<BillingStructure>() {

    @Override
    public int compare(BillingStructure o1, BillingStructure o2) {
        return ((Double) o1.getAmount()).compareTo(o2.getAmount());
    }

});out.writeObject(hm.get(max.getPhoneNo()).getName());}}

// ******************** TESTER ********************

package tester;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.HashMap;
import static utils.CollectionUtils.populateData;
import static utils.IOUtils.*;
import static java.time.LocalDate.*;
import com.app.core.PhoneBill;

public class TestServer {

    public static void main(String[] args) {
        System.out.println("Month: " + LocalDate.now().getMonth());
        System.out.println("waiting for client...");
        HashMap<String, PhoneBill> clientBills = populateData();
        try (ServerSocket ss = new ServerSocket(7777, 10); Socket ds = ss.accept()) {
            System.out.println("cn accepted from Clnt IP " + ds.getInetAddress().getHostName() + " rem port "
                    + ds.getPort() + " server port  " + ds.getLocalPort());
            try (ObjectOutputStream out = new ObjectOutputStream(ds.getOutputStream())) {
                out.flush();
                DataInputStream in = new DataInputStream(ds.getInputStream());
                while (true) {
                    try {
                        switch (in.readInt()) {
                        case 1:// Client sends his/her mobile number to TCP server.
                               // Server sends back current month bill.
                            sendCurrentMonthBill(out, clientBills.get(in.readUTF()).getMonthlyBills());
                            break;
                        case 2:// Client sends his/her mobile number to TCP server.
                               // Server sends back bills for last 6 months
                            sendSixMonthsBill(out, clientBills.get(in.readUTF()).getMonthlyBills());
                            break;
                        case 3:// Client sends his/her name & birth date . Server sends back current month bill
                               // .
                               // ArrayList<PhoneBill> l1 = (ArrayList<PhoneBill>) clientBills.values();
                            String name = in.readUTF();
                            String dob = in.readUTF();
                            for (PhoneBill pb : clientBills.values()) {
                                if (pb.getName().equals(name) && pb.getDob().equals(parse(dob))) {
                                    sendSixMonthsBill(out, pb.getMonthlyBills());
                                    break;
                                }
                            }
                            break;
                        case 4: // Admin user asks for the name of user with max bill in past 6 month, server
                                // sends it back.
                            maxPhoneBill(out, clientBills);
                            break;
                        default:
                            break;
                        }
                    } catch (Exception e) {
                        out.writeUTF("Error No data Found");
                        e.printStackTrace();
                    }

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
