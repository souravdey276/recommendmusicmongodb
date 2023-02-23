package com.dxc.exception;

public class MusicDoesNotExistsException extends Exception {
	
	//serialVersionUID is something like version control, 
	//assure both serialized and deserialized objects are using the compatible class 

	private static final long serialVersionUID = 1L;

	public MusicDoesNotExistsException(String message) {
        super(message);
    }
}
