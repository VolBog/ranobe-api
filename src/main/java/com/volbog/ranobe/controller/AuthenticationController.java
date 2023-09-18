package com.volbog.ranobe.controller;

import com.volbog.ranobe.dto.AuthenticationRequestDto;
import com.volbog.ranobe.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

  @GetMapping("/login")
  public String login(@RequestParam(required = false) final Boolean loginRequired,
      @RequestParam(required = false) final Boolean loginError,
      @RequestParam(required = false) final Boolean logoutSuccess, final Model model) {
    model.addAttribute("authentication", new AuthenticationRequestDto());
    if (loginRequired == Boolean.TRUE) {
      model.addAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("authentication.login.required"));
    }
    if (loginError == Boolean.TRUE) {
      model.addAttribute(WebUtils.MSG_ERROR, WebUtils.getMessage("authentication.login.error"));
    }
    if (logoutSuccess == Boolean.TRUE) {
      model.addAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("authentication.logout.success"));
    }
    return "authentication/login";
  }

  @ModelAttribute("requestUri")
  String getRequestServletPath(final HttpServletRequest request) {
    return request.getRequestURI();
  }

}
