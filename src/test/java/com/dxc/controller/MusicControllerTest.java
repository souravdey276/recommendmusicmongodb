package com.dxc.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dxc.exception.MusicDoesNotExistsException;
import com.dxc.exception.MusicNotAddedException;
import com.dxc.model.Music;
import com.dxc.service.MusicService;
import com.fasterxml.jackson.databind.ObjectMapper;







/*    
 * The following annontation 
 * @RunWith(SpringRunner.class) tells Junit to run using Spring's testing support

 * 
 */

@RunWith(SpringRunner.class)

/*
 * The following annotation @WebMvcTest is to fire up an application context ( IOC )
 * that contains only the beans needed for testing a web controller
 * 
 */

@WebMvcTest
public class MusicControllerTest {
	
	
@Autowired
	
	/* the below is the main entry point for server-side Spring MVC test support  */
	private MockMvc mockMvc;
	private Music music;
	
	
	/* The below annotation is used to which object needs to be mocked */
	@MockBean
	private MusicService musicService;
	
	
	/* Mockito's @injectMocks annotations allow us to inject mocked dependencies  */
	@InjectMocks
	
	private MusicController musicController;
	private List<Music> allMusic=null;
	



	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(musicController).build();
		music=new Music();
		music.setId("tra.245806622");
		music.setAlbumName("Stoney (Deluxe)");
		music.setArtistName("Post Malone");
	}

	@Test
	public void addMusicSuccess() throws Exception {
		
		when(musicService.addMusic(any())).thenReturn(music);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/music/addMusic")
		.contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
		.andExpect(MockMvcResultMatchers.status().isCreated())
		
		.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void addMusicFailure() throws Exception{
	
		when(musicService.addMusic(any())).thenThrow(MusicNotAddedException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/music/addMusic")
		.contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
		.andExpect(MockMvcResultMatchers.status().isConflict())
		.andDo(MockMvcResultHandlers.print());
	}
	
	
	
	
	 @Test
	    public void deleteMusicSuccess() throws Exception {

	        when(musicService.deleteMusic(any())).thenReturn(music);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/music/delete/tra.245806622")
	                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }


	    @Test
	    public void deleteMusicFailure() throws Exception {

	        when(musicService.deleteMusic(any())).thenThrow(MusicDoesNotExistsException.class);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/music/deleteMusic/tra.24580")
	                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound())
	                .andDo(MockMvcResultHandlers.print());
	    }
	    
	    
	    
	    @Test
	    public void getMusic() throws Exception
	    {
	    	when(musicService.getMusic()).thenReturn(allMusic);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/music/getMusic")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }
	
	


	/*@Test
	public void testGetMusic() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMusic() {
		fail("Not yet implemented");
	}*/
	
	
	
	
	private String asJsonString(Music music) {
		// TODO Auto-generated method stub
		try {
			return new ObjectMapper().writeValueAsString(music);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	
	}

}
