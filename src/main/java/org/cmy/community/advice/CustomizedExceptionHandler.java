package org.cmy.community.advice;

import com.alibaba.fastjson.JSON;
import org.cmy.community.dto.ResultDTO;
import org.cmy.community.exception.CustomizedErrorCode;
import org.cmy.community.exception.CustomizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizedExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //返回json对象
            ResultDTO resultDTO;
            if (ex instanceof CustomizedException) {
                resultDTO = ResultDTO.errorOf((CustomizedException) ex);
            }else {
                resultDTO =  ResultDTO.errorOf(CustomizedErrorCode.SYSTEM_ERRO);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
//                e.printStackTrace();
            }
            return null;
        }else{
            //跳转到错误页面
            if (ex instanceof CustomizedException) {
                model.addAttribute("message", ex.getMessage());
            }else {
                model.addAttribute("message",CustomizedErrorCode.SYSTEM_ERRO.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
