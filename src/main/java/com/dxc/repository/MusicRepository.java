package com.dxc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dxc.model.Music;

//@Repository annotates classes at the persistence layer, which will act as a database repository.
@Repository
public interface MusicRepository extends MongoRepository<Music, String> {
	
	//we have taken music ID as string here.

}
