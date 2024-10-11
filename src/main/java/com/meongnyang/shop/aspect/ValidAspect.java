package com.meongnyang.shop.aspect;

import com.meongnyang.shop.dto.request.ReqUserSignupDto;
import com.meongnyang.shop.exception.ValidException;
import com.meongnyang.shop.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ValidationException;

@Component
@Aspect
public class ValidAspect {

    @Autowired
    private UserService userService;

    @Pointcut("@annotation(com.meongnyang.shop.aspect.annotation.ValidAop)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;

        for (Object arg : args) {
            if (arg instanceof BeanPropertyBindingResult) {
                bindingResult = (BeanPropertyBindingResult) arg;
                break;
            }
        }
        switch (joinPoint.getSignature().getName()) {
            case "adminSignin":
                break;
            case "signup":
                signupValid(args, bindingResult);
                break;
        }

        if (bindingResult.hasErrors()) {
            throw new ValidException("유효성 검사 오류", bindingResult.getFieldErrors());
        }

        return joinPoint.proceed();
    }

    private void signupValid(Object[] args, BindingResult bindingResult) {
        for(Object arg : args) {
            if (arg instanceof ReqUserSignupDto) {
                ReqUserSignupDto dto = (ReqUserSignupDto) arg;
                FieldError fieldError = null;
                if (!dto.getPassword().equals(dto.getCheckPassword())) {
                    fieldError = new FieldError("checkPassword", "checkPassword", "비밀번호를 확인해주세요");
                    bindingResult.addError(fieldError);
                }
                if (!userService.isDuplicationUsername(dto.getUsername())) {
                    fieldError = new FieldError("username", "username", "중복된 아이디입니다.");
                    bindingResult.addError(fieldError);
                }
            }
        }
    }
}
