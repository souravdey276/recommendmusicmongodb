package com.dxc.repository;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dxc.model.Music;


/* 
 * @RunWith(SpringRunner.class) tells JUnit
 * to run using's Spring's testing support 
 * */
@RunWith(SpringRunner.class)

/* We are testing for MongoDB */
@DataMongoTest
public class MusicRepositoryTest {
	
	@Autowired
	private MusicRepository repository;
	private Music music;

	@Before
	public void setUp() throws Exception {
		
		music=new Music();
		music.setId("tra.245806622");
		music.setAlbumName("Stoney (Deluxe)");
		music.setArtistName("Post Malone");
	}
	
	@After
	public void tearDown() throws Exception{
		repository.deleteAll();
	}
	
	
	
	// Add testcase for CRUD operation
		@Test
		public void addMusicTest()
		{
			// currently we are testing with the music object initialised in the setUp method
			repository.save(music);
			Music addMusic=repository.findById(music.getId()).get();
			assertEquals("tra.245806622",addMusic.getId());
		}
		
		
		// DELETE testcase for CRUD operation 
		   @Test(expected=NoSuchElementException.class)
			public void deleteMusicTest() {
				// 1) Inserting one record
			     repository.save(music);
			     // 2) Fetch the record to be deleted!!!!
			     Music deleteMusic=repository.findById(music.getId()).get();
			     assertEquals("tra.245806622",deleteMusic.getId());
			     // 3) Delete the record!!!
			     repository.delete(deleteMusic);
			     deleteMusic=repository.findById(music.getId()).get();
		   }
		   
		   
		   

	
}
