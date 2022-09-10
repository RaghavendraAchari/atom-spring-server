package com.raghav.atom.security.jwt;

import com.raghav.atom.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity authenticate(@RequestBody JwtRequest request) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUserName(),
                    request.getPassWord()
            ));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username and password", e);
        }

        final UserDetails userDetails = userDetailsService.
                loadUserByUsername(request.getUserName());

        final String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

//    @RequestMapping(value = "/user/{username}",method = RequestMethod.GET)
//    public ResponseEntity getUser(@PathVariable(value = "username") String userName){
//        System.out.println(userName);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//        return ResponseEntity.ok(userDetails);
//    }
}
