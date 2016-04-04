package com.tii.trustcomputation.metric;

import java.io.Serializable;

public class Knowledge implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String description;
	private int level; //Knowledge Trust Metric (TM) level
	private int plevel; //Physical sub-TM level
	private int clevel; //Cyber sub-TM level
	private int slevel; //Social sub-trust metric level
	private String timestamp;
	private Object dataCloud;
	
	public Knowledge(String id, String description, int level, int plevel, int clevel, int slevel, 
			String timestamp, Object dataCloud) {
		super();
		this.id = id;
		this.description = description;
		this.level = level;
		this.plevel = plevel;
		this.clevel = clevel;
		this.slevel = slevel;

		this.timestamp =  timestamp;
		this.dataCloud = dataCloud;
	}
	
	//set properties functions
	
		public void setId(String id){
			this.id = id;
		}	
		public void setDescription(String description){
			this.description = description;
		}
		public void setLevel(int level){
			this.level = level;
		}
		public void setpLevel(int plevel){
			this.plevel = plevel;
		}
		public void setcLevel(int slevel){
			this.slevel = slevel;
		}
		public void setsLevel(int clevel){
			this.clevel = clevel;
		}
		public void setTimestamp(String timestamp){
			this.timestamp = timestamp;
		}
		public void setDataCloud(Object dataCloud){
			this.dataCloud = dataCloud;
		}
		
	//get properties functions
		public String getId(){
			return id;
		}
		public String getDescription(){
			return description;
		}
		public int getLevel(){
			return level;
		}
		public int getpLevel(){
			return plevel;
		}
		public int getcLevel(){
			return clevel;
		}
		public int getsLevel(){
			return slevel;
		}
		public String getTimestamp(){
			return timestamp;
		}
		public Object getDataCloud(){
			return dataCloud;
		}
	
	///////////////////////////	
		@Override
		public int hashCode() {
			int _hash = 0;
			_hash += (getId() != null ? getId().hashCode() : 0);
			return _hash;
		}

		@Override
		public boolean equals(Object _object) {
			if (!(_object instanceof Knowledge)) {
				return false;
			}
			Knowledge _other = (Knowledge) _object;
			if ((this.getId() == null && _other.getId() != null)
					|| (this.getId() != null && !this.getId()
							.equals(_other.getId()))) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return "Knowledge[id=" + getId() + "]" + serialVersionUID;
		}
}