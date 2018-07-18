/**
 * 
 */
package com.wc.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

/**
 * @author pavankumarv
 *
 */
public class Writer implements ItemWriter<String> {

	public void write(List<? extends String> messages) throws Exception {
		for (String msg : messages) {
			System.out.println("Writing the data " + msg);
		}
	}
}
