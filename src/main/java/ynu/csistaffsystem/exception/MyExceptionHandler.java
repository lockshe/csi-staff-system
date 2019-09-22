package ynu.csistaffsystem.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<String> authError(AuthenticationException authenticationException){
        LOGGER.info(authenticationException.getMessage());
        return new ResponseEntity<>(authenticationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> myException(MyException myException){
        LOGGER.info(myException.getMsg());
        return new ResponseEntity<>(myException.getMsg(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> roleError(UnauthorizedException exception){
        LOGGER.info(exception.getMessage());
        return new ResponseEntity<>("没有对应权限",HttpStatus.UNAUTHORIZED);
    }

}
