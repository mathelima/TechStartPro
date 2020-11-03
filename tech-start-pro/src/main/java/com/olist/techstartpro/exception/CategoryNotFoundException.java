package com.olist.techstartpro.exception;

public class CategoryNotFoundException extends RuntimeException{

        public CategoryNotFoundException(String message){
            super(message);
        }
}
