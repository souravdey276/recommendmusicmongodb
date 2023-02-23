package com.dxc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.exception.MusicDoesNotExistsException;
import com.dxc.exception.MusicNotAddedException;
import com.dxc.model.Music;
import com.dxc.repository.MusicRepository;


//to write the business logic to store, retrieve, delete and updates the product.
@Service
public class MusicServiceImpl implements MusicService {
	
	//@Autowired the ProductService interface and called the methods.
	@Autowired
	private MusicRepository repository;
	

	/*@Override
	public Music addMusic(Music music) {
		 repository.save(music);
		return music;
	}

	@Override
	public List<Music> getMusic() {
		
		return repository.findAll();
	}*/
	
	
	@Override
	public Music addMusic(Music music) throws MusicNotAddedException {
		Music addMusic= repository.save(music);
		if(addMusic!=null)
		{
			return addMusic;
		}else
		{
			throw new MusicNotAddedException("Music Not Added");
		}
	}

	//@override is used If programmer makes any mistake such as wrong method name, wrong parameter types while overriding, you would get a compile time error
	@Override
	public List<Music> getMusic() {
		
		return repository.findAll();
	}
	
	
	

	
	
	/*@Override;
	public Music deleteMusic(String musicId) 
	{
		
		Optional<Music> isMusicPresent = repository.findById(musicId);
		if(isMusicPresent.isPresent()) 
		{
			 Music music=isMusicPresent.get();
		        repository.delete(music);
					return true;	
		}
		return false;

       
	}*/
	
	
	/*@Override
	public Music deleteMusic(String musicId) {
		Music musicToDelete = repository.findById(musicId).get();
		if(musicToDelete!=null)
		{
			repository.delete(musicToDelete);
		}
		return musicToDelete;
	}*/
	
	
	@Override
	public Music deleteMusic(String musicId) throws MusicDoesNotExistsException {
		Music musicToDelete = repository.findById(musicId).get();
		if(musicToDelete==null)
		{
			throw new MusicDoesNotExistsException("Music Does Not Exists");
		}else {
			repository.delete(musicToDelete);
		}
		return musicToDelete;
	}
	
	
	

}
