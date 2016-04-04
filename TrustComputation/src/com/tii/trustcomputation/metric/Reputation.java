package com.tii.trustcomputation.metric;

import java.io.Serializable;

public class Reputation implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String description;
	private int level;
	private String levelRange;	
	private String timestamp;
	private Object dataCloud;
}