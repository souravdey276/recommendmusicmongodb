package com.dxc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Setup and Configure Spring Data MongoDB.
@Document
public class Music {
	//The @Id annotation marks a field as a primary key field. When a primary key field is defined the primary key value is automatically injected into that field by ObjectDB.
	@Id
	 private String id;
	private String artistName;
	private String email;
		
		private String albumName;
		
		
		//generated getter and setter
		//for id
		//for artistName
		//for albumName
		// for email

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getArtistName() {
			return artistName;
		}

		public void setArtistName(String artistName) {
			this.artistName = artistName;
		}

		public String getAlbumName() {
			return albumName;
		}

		public void setAlbumName(String albumName) {
			this.albumName = albumName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		
		
		
		
		

}
