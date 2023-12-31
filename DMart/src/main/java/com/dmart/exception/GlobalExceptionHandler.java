package com.dmart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* --------------------------------------   Login Exception    ----------------------------------------------*/
    @ExceptionHandler(LogInException.class)
    public ResponseEntity<ErrorDetails> logInException(LogInException loginException, WebRequest request){

        ErrorDetails err=new ErrorDetails(LocalDateTime.now(), loginException.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    /* --------------------------------------   Validation Exception    ----------------------------------------------*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> validationException(MethodArgumentNotValidException validationException, WebRequest request){

        ErrorDetails err=new ErrorDetails(LocalDateTime.now(), validationException.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    /*----------------------------------------NoHandellerFoundException--------------------------------------*/
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> mynotFoundHandler(NoHandlerFoundException noHandlerFoundException, WebRequest request) {
        ErrorDetails err=new ErrorDetails(LocalDateTime.now(), noHandlerFoundException.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    /* --------------------------------------   UserSession Exception    ----------------------------------------------*/
    @ExceptionHandler(UserSessionException.class)
    public ResponseEntity<ErrorDetails> userException(UserSessionException userException, WebRequest request){

        ErrorDetails err=new ErrorDetails(LocalDateTime.now(), userException.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
//   

    /* --------------------------------------   Login Exception    ----------------------------------------------*/
//    @ExceptionHandler(LogOutException.class)
//    public ResponseEntity<ErrorDetails> logOutException(LogOutException logOutException, WebRequest request){
//
//        ErrorDetails err=new ErrorDetails(LocalDateTime.now(), logOutException.getMessage(), request.getDescription(false));
//
//        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
//
//    }

 
   


    /*--------------------------------------------  Exception  --------------------------------------------------*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> Exception(Exception exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    /*--------------------------------------------  Null Pointer Exception  --------------------------------------------------*/
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetails> NullPointerException(NullPointerException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    /* --------------------------------------   StockItem Exception    ----------------------------------------------*/
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> StockItemException(StockItemException userException, WebRequest request){

        ErrorDetails err=new ErrorDetails(LocalDateTime.now(), userException.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }
    
    @ExceptionHandler(AdminException.class)
    public ResponseEntity<ErrorDetails> Exception(AdminException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    
}
