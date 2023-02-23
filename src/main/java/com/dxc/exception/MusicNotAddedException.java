package com.dxc.exception;

public class MusicNotAddedException extends Exception {
   
	
	//serialVersionUID is something like version control, 
	//assure both serialized and deserialized objects are using the compatible class 
	private static final long serialVersionUID = 1L;

	public MusicNotAddedException(String message) {
        super(message);
    }
}
