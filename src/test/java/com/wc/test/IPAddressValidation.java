package com.wc.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class IPAddressValidation {
		
	/*@Test
		public void validateIp() {
			SubnetUtils subnetUtils = new SubnetUtils("192.168.0.1/32");
			boolean isInRange = subnetUtils.getInfo().isInRange("172.21.6.241");
			System.out.println(isInRange);
		
		SubnetUtils utils = new SubnetUtils( "10.0.75.1/32" );
	   final SubnetUtils.SubnetInfo info = utils.getInfo();
	    
	    
	    Assert.assertEquals( "10.0.75.1", info.getAddress() );
	    Assert.assertEquals( "10.0.75.0", info.getNetworkAddress() );
	    Assert.assertEquals( "10.0.75.255", info.getBroadcastAddress() );
	    Assert.assertEquals( 254, info.getAddressCount() );
	    Assert.assertEquals( "10.0.75.1/24", info.getCidrSignature() );
	    Assert.assertEquals( "10.0.75.1", info.getLowAddress() );
	    Assert.assertEquals( "10.0.75.254", info.getHighAddress() );
	    Assert.assertEquals( "255.255.255.0", info.getNetmask() );
	    Assert.assertEquals( true, info.isInRange( "10.0.75.1" ) );
	    Assert.assertEquals( false, info.isInRange( "10.11.0.1" ) );
		}
	*/
	
	@Test
	public void validateIp1() {
		 String ipAddressReq	=	"10.10.17.68";
		 String ipAddresses = "10.10.17.0/24";
		 
		 List<String> listOfIpAddress = new ArrayList(Arrays.asList(ipAddresses.split(",")));
		 
		 boolean isValidIpRange	=	false;
		 try{
			 
		isValidIpRange	=	listOfIpAddress.stream().anyMatch(ipAddressObj -> {
			
			String cidr	=	ipAddressObj!=null ? ipAddressObj.toString():null;
			
			if (cidr!=null && cidr.indexOf('/') > -1){
				SubnetUtils utils = new SubnetUtils( cidr );
			    SubnetUtils.SubnetInfo info = utils.getInfo();
			    
			    System.out.println("info.getLowAddress()>>>"+info.getLowAddress());
			    System.out.println("info.getHighAddress()>>>"+info.getHighAddress());
			    
			    System.out.println("ipAddressReq>>>"+ipAddressReq);
			    System.out.println("listOfIpAddress>>>"+listOfIpAddress);
			    
			    if(info.isInRange(ipAddressReq)){
					System.out.println("ipAddressReq:::::"+ipAddressReq);
			    }
			}
			if (cidr.indexOf('/') == -1)
				cidr = cidr + "/24";
			
			SubnetUtils utils = new SubnetUtils( cidr );
		    SubnetUtils.SubnetInfo info = utils.getInfo();
		    System.out.printf("Address List: %s\n\n", Arrays.toString(info.getAllAddresses()));
		    if(info.isInRange(ipAddressReq)){
				return true;
		    }else if(cidr.equals(ipAddressReq)){
		    	return true;
		    }else {
		    	return false;
		    }
		});

		 }catch( Exception ex){
			 ex.printStackTrace();
			 System.err.println("Failed to validate ip range"+"\n ipAddresses="+ipAddresses+"\n ipAddress="+ipAddressReq);
		 }
		
		//==========================================================
		
		if(isValidIpRange){
			System.out.println("Valid IP in range");
		}else {
			System.out.println("Not Valid IP in range");
		}
		
		
		
		
		
		 String ipRange = "10.10.17.68/24";
		  String clientIp = "10.10.16.68";
		  SubnetUtils utils = new SubnetUtils(ipRange);
		  utils.setInclusiveHostCount(true);
		  System.out.println("lower address: " + utils.getInfo().getLowAddress());
		  System.out.println("higher address: " + utils.getInfo().getHighAddress());
		  System.out.println(utils.getInfo().isInRange(clientIp));
		
		
		

	}

	

}
