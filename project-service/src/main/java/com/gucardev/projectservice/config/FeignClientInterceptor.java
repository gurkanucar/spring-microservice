package com.gucardev.projectservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class FeignClientInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate template) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      String username = authentication.getName();
      template.header("X-Authenticated-User", username);

      if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
        // Convert authorities to a set of authority names
        String authorities =
            authentication.getAuthorities().stream()
                .map(Object::toString)
                .reduce((s1, s2) -> s1 + "," + s2)
                .orElse("");
        template.header("X-Authenticated-Roles", authorities);
      }
    } else {
      template.header("X-Service-Request", "true");
    }
  }
}
