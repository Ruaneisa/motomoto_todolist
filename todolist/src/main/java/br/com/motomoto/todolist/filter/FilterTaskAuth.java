package br.com.motomoto.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.motomoto.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

	@Autowired
	private IUserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String path = request.getRequestURI();

		if (path.startsWith("/swagger") || path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")
				|| path.startsWith("/swagger-ui.html")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (path.startsWith("/tasks/")) {

			String authorization = request.getHeader("Authorization");

			if (authorization == null || !authorization.startsWith("Basic ")) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header missing or invalid");
				return;
			}

			try {
				String authEncoded = authorization.substring("Basic".length()).trim();
				byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
				String authString = new String(authDecoded);

				String[] credentials = authString.split(":", 2);
				if (credentials.length < 2) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid authorization format");
					return;
				}

				String username = credentials[0];
				String password = credentials[1];

				var user = this.userRepository.findByUsername(username);
				if (user == null) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
					return;
				}

				var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
				if (!passwordVerify.verified) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid password");
					return;
				}

				request.setAttribute("idUser", user.getId());

				filterChain.doFilter(request, response);

			} catch (IllegalArgumentException e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Failed to decode authorization");
			}

			return;

		} else {

			filterChain.doFilter(request, response);
		}
	}
}
