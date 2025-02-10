package gateway_server.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeyCloakResourceConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert(Jwt source) {

		Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");

		if (realmAccess == null || realmAccess.isEmpty()) {
			return new ArrayList<>();
		} else {
			Collection<GrantedAuthority> authorities = ((List<String>) realmAccess.get("roles")).stream()
					.map(rolename -> "ROLE_".concat(rolename)).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
			return authorities;
		}
	}

}
