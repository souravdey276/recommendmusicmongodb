package com.dxc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;


import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean {

	//Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZG1pbkBnbWFpbC5jb20iLCJzdWIiOiJhZG1pbiIsImlhdCI6MTU5NzEyNDg1NH0.uef7t0nJj-sdKdLNQyZQ3q9LOFi1JGGz9u55u3NweYM"
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		
		//final is non access modifier 
		//variable can't be modified it will be constant
		//http client is making request to the server

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		  filterChain.doFilter(req, res);
		} else {
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid Authorization header");
			}

			final String token = authHeader.substring(7);
			final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();

			request.setAttribute("claims", claims);
			filterChain.doFilter(req, res);

		}

	}



}
