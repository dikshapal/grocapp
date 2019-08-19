package com.example.demo.resource;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginResource {
    @Autowired
    private UserService userService;

    @RequestMapping("/token/{user}")
    public Map<String, String> token(HttpSession session, HttpServletRequest request,@PathVariable String user) {
        System.out.println(request.getRemoteHost());

        String remoteHost = request.getRemoteHost();
        int portNumber = request.getRemotePort();
        System.out.println(user);
        session.setAttribute("user", user);
        System.out.println(remoteHost+":"+portNumber);
        System.out.println(request.getRemoteAddr());
        
        return Collections.singletonMap("token", session.getId());
    }

    @RequestMapping("/checkSession")
    public ResponseEntity checkSession() {
        return new ResponseEntity<>("Session Active!", HttpStatus.OK);
    }

    @RequestMapping(value="/user/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpSession session){
        session.invalidate();
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Logout Successfully!", HttpStatus.OK);

    }
}
