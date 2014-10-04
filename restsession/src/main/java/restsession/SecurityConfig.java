package restsession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.SessionRepositoryFilter;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RedisOperationsSessionRepository sessionRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		final SessionRepositoryFilter sessionRepositoryFilter = new SessionRepositoryFilter(sessionRepository);
		sessionRepositoryFilter.setHttpSessionStrategy(new HeaderHttpSessionStrategy());

		http
			.addFilterBefore(sessionRepositoryFilter, ChannelProcessingFilter.class)
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
}
