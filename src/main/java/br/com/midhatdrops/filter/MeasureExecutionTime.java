package br.com.midhatdrops.filter;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MeasureExecutionTime extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws ServletException, IOException {

    LocalTime preTime = LocalTime.now();
    String requestURI = req.getRequestURI();
    chain.doFilter(req, res);
    LocalTime postTime = LocalTime.now();

    long delta = preTime.until(postTime, ChronoUnit.MILLIS);
    System.out.println("[RequestURI: " + requestURI + " " + delta + " ms ] ");

  }

}
