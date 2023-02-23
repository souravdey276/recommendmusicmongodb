package com.dxc.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dxc.model.Music;
import com.dxc.repository.MusicRepository;

import com.dxc.exception.MusicNotAddedException;



public class MusicServiceImplTest {

private Music music;
	
	/* We are going to have a mock object of MusicRepository */
	
	@Mock
	private MusicRepository repository;

	/*  Mockito @InjectMocks annotation allow us to inject
	 mocked dependencies object.   */
	
	@InjectMocks
	private MusicServiceImpl musicServiceImpl;

	private List<Music> allMusic=null;
	Optional<Music>  options;
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		music=new Music();
		music.setId("tra.245806622");
		music.setAlbumName("Stoney (Deluxe)");
		music.setArtistName("Post Malone");
		options=Optional.of(music);
		
	}
	
	@Test
	public void addMusicTestSuccess() throws Exception{
		
		when(repository.save((Music)any())).thenReturn(music);
		Music savedMusic=musicServiceImpl.addMusic(music);
		assertEquals(music,savedMusic);
	}
	
	@Test(expected=MusicNotAddedException.class)
	public void addMusicTestFailure() throws Exception {
		when(repository.save((Music)any())).thenReturn(null);
		Music savedMusic=musicServiceImpl.addMusic(music);
		assertEquals(music,savedMusic);
	}
	
	
	@Test
	public void deleteMusic() throws Exception {
	when(repository.findById(music.getId())).thenReturn(options);	
	Music deleteMusic=musicServiceImpl.deleteMusic(music.getId());
	assertEquals(music,deleteMusic);
	}
}
	
	

	


