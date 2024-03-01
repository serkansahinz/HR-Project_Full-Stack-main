package com.hrproject.config.security;

import com.hrproject.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

  private final JwtTokenManager jwtTokenManager;
  private final JwtUserDetails jwtUserDetails;

  @Override
  protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {

	String authorizationHeader = request.getHeader("Authorization");

	if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
	  //   && SecurityContextHolder.getContext().getAuthentication()==null
	) {
	  String token = authorizationHeader.substring(7);
	  Optional<Long> id = jwtTokenManager.getIdFromToken(token);
	  UserDetails userDetails = null;

	  if (id.isPresent()) {
		userDetails = jwtUserDetails.loadUserByUserId(id.get());
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	  }

	}


	filterChain.doFilter(request,response);
  }
}
