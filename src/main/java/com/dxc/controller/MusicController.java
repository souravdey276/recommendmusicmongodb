package com.dxc.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.dxc.exception.MusicDoesNotExistsException;
import com.dxc.exception.MusicNotAddedException;
import com.dxc.model.Music;
import com.dxc.service.MusicService;



  //@RestController is a convenience annotation for creating Restful controllers
	@RestController
	
	//@CrossOrigin, We use this in our learning for integration of front end ,back end with databases.
	@CrossOrigin(origins="http://localhost:4200")
	
	//maps HTTP requests to handler methods of MVC and REST controllers
	@RequestMapping("/api/music")
	
	public class MusicController {	
		//used for automatic dependency injection, and injecting the class dependencies through spring bean configuration file
		@Autowired
		private MusicService musicService;
		
		//maps HTTP POST requests
		@PostMapping("/addMusic")
		public ResponseEntity<?> addMusic(@RequestBody Music music){
			
			try {
				Music addMusic = musicService.addMusic(music);
				if(addMusic!=null)
				{
					return new ResponseEntity<>(music, HttpStatus.CREATED);
				}
			} catch (MusicNotAddedException e) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			return new ResponseEntity<>(music, HttpStatus.CREATED);
		}
			
		/*public Music addMusic(@RequestBody Music music)
		{
			
			Music musicToAdd = musicService.addMusic(music);
			return musicToAdd;
		}*/
		
		
		//maps HTTP GET requests
		//collection is used for get music
		@GetMapping("/getMusic")
		public ResponseEntity<?> getMusic()
		{
			List<Music> music=musicService.getMusic();
			return new ResponseEntity<>(music,HttpStatus.OK);
		}
		
		
		
		/*@GetMapping("/getMusic/{email}")
		public ResponseEntity<?> getMusic(@PathVariable String email)
		{
			List<Music> allMusic =musicService.getMusic();
			List<Music> getMusic=new ArrayList<>();
			
			for(Music music : allMusic)
			{
				if(music.getEmail().equals(email))
				{
					getMusic.add(music);
				}
			}
			return new ResponseEntity<>(getMusic,HttpStatus.OK);
		}*/
		
		
		
		@GetMapping("/getFavoriteMusic/{email}")
		public ResponseEntity<?> getFavoriteMusic(@PathVariable String email)
		{
			
			List<Music> allmusic=musicService.getMusic();
			List<Music> favoriteMusic=new ArrayList<>();
			
		    for (Music music : allmusic) {
				if(music.getEmail().equals(email));
				{
					favoriteMusic.add(music);
				}
			}
			return new ResponseEntity<>(favoriteMusic,HttpStatus.OK);
		}
			
		
		
		
		
		//maps HTTP DELETE requests
		@DeleteMapping("/delete/{musicId}")
		public  ResponseEntity<?> deleteMusic(@PathVariable String musicId)
		{
			try {
				Music musicToDelete=musicService.deleteMusic(musicId);
			 if (musicToDelete!=null) {
				 return new ResponseEntity<>(HttpStatus.OK);
			 }
		}catch (MusicDoesNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		}
	}
		
	


