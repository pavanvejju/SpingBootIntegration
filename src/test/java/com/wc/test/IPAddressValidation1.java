package com.wc.test;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;



public class IPAddressValidation1 {
		
		public static void main(String[] args) {
			
			
			String subnet = "10.10.17.0/24";
			if (subnet.isEmpty())
				return;
			if (subnet.indexOf('/') == -1)
				subnet = subnet + "/24";
			
			
	      
	        SubnetUtils utils = new SubnetUtils(subnet);
	        SubnetInfo info = utils.getInfo();
	       
	       /* System.out.printf("Subnet Information for %s:\n", subnet);
	        System.out.println("--------------------------------------");
	        System.out.printf("IP Address:\t\t\t%s\t[%s]\n", info.getAddress(),
	                Integer.toBinaryString(info.asInteger(info.getAddress())));
	        System.out.printf("Netmask:\t\t\t%s\t[%s]\n", info.getNetmask(),
	                Integer.toBinaryString(info.asInteger(info.getNetmask())));
	        System.out.printf("CIDR Representation:\t\t%s\n\n", info.getCidrSignature());
	       
	        System.out.printf("Supplied IP Address:\t\t%s\n\n", info.getAddress());
	       
	        System.out.printf("Network Address:\t\t%s\t[%s]\n", info.getNetworkAddress(),
	                Integer.toBinaryString(info.asInteger(info.getNetworkAddress())));
	        System.out.printf("Broadcast Address:\t\t%s\t[%s]\n", info.getBroadcastAddress(),
	                Integer.toBinaryString(info.asInteger(info.getBroadcastAddress())));
	        System.out.printf("First Usable Address:\t\t%s\t[%s]\n", info.getLowAddress(),
	                Integer.toBinaryString(info.asInteger(info.getLowAddress())));
	        System.out.printf("Last Usable Address:\t\t%s\t[%s]\n", info.getHighAddress(),
	                Integer.toBinaryString(info.asInteger(info.getHighAddress())));
	       
	        System.out.printf("Total usable addresses: \t%d\n", info.getAddressCount());
	        System.out.printf("Address List: %s\n\n", Arrays.toString(info.getAllAddresses()));*/
	      
	        
	        
	        System.out.println("Boolean.valueOf(info.isInRange(ipAddressReq)>>"+Boolean.valueOf(info.isInRange("10.10.16.68")));
	        
	        /*
	        final String prompt ="Enter an IP address (e.g. 10.0.75.10):";
	        System.out.println(prompt);
	        Scanner scanner = new Scanner(System.in);
	        while (scanner.hasNextLine()) {
	            String address = scanner.nextLine();
	            System.out.println("The IP address [" + address + "] is "
	                    + (info.isInRange(address) ? "" : "not ")
	                    + "within the subnet [" + subnet + "]");
	            
	            
	            System.out.println("Boolean.valueOf(info.isInRange(ipAddressReq)>>"+Boolean.valueOf(info.isInRange(address)));
	            System.out.println(prompt);
	        }*/
	        
	      
	        
	       
	    }
	}
