//package net.enjoy.springboot.registrationlogin.exception;
//
//import net.enjoy.springboot.registrationlogin.dto.ExceptionDto;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(value = Exception.class)
//        ResponseEntity<ExceptionDto> hanglingRuntimeException(RuntimeException exception){
//        ExceptionDto exceptionDto = new ExceptionDto();
//
//        exceptionDto.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getErrorCode());
//        exceptionDto.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getErrorMessage());
//
//        return ResponseEntity.badRequest().body(exceptionDto);
//    }
//
//    @ExceptionHandler(value = AppException.class)
//        ResponseEntity<ExceptionDto> hanglingException(AppException exception){
//        ErrorCode errorCode = exception.getErrorCode();
//        ExceptionDto exceptionDto = new ExceptionDto();
//
//        exceptionDto.setCode(errorCode.getErrorCode());
//        exceptionDto.setResult(errorCode.getErrorMessage());
//
//        return ResponseEntity.badRequest().body(exceptionDto);
//    }
//
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//        ResponseEntity<String> hanglingValidation(MethodArgumentNotValidException exception){
//            return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage());
//        }
//
//}
