package com.dxc.service;

import java.util.List;

import com.dxc.exception.MusicDoesNotExistsException;
import com.dxc.exception.MusicNotAddedException;
import com.dxc.model.Music;




public interface MusicService {

	/*public Music addMusic(Music music);
	public List<Music> getMusic();
	public Music deleteMusic(String musicId);*/
	
	
	
	public Music addMusic(Music music) throws MusicNotAddedException;
	public List<Music> getMusic();
	public Music deleteMusic(String musicId) throws MusicDoesNotExistsException;

}
