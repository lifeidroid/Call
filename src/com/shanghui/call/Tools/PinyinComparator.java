package com.shanghui.call.Tools;

import java.util.Comparator;

import com.shanghui.call.Mdl.Mdl_Contact;


/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<Mdl_Contact> {

	public int compare(Mdl_Contact o1, Mdl_Contact o2) {
		if (o1.getFirstNamePy().equals("@")|| o2.getFirstNamePy().equals("#")) {
			return -1;
		} else if (o1.getFirstNamePy().equals("#")|| o2.getFirstNamePy().equals("@")) {
			return 1;
		} else {
			return o1.getFirstNamePy().compareTo(o2.getFirstNamePy());
		}
	}

}
