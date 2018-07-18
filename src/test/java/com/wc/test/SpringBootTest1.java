/**
 * 
 */
package com.wc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wc.pojo.User;
import com.wc.service.impl.UserService;

/**
 * @author pavankumarv
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTest1 {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void contextLoads(){
		User u	=	userService.testFetchThread(1L);
		if(u==null) {
			System.out.println("No User");
		}
		
		System.out.println(">>>>>>>>>>>>>>>>"+u.getFirstName());
		System.out.println(">>>>>>>>>>>>>>>>"+u.getLastName());
	}

}
