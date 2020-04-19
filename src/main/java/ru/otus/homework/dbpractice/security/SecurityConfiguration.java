package ru.otus.homework.dbpractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.otus.homework.dbpractice.utils.Resources;

import javax.sql.DataSource;

@CrossOrigin(origins = {"http://localhost:3000"})
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and().httpBasic();
        //логика постраничного перемещания реализована на компонентах роуттинга в реакте
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        /* для демонстрации не используем кодирование и шифрование, храним в открытом виде
        * в том числе для удобсва инсерта и проверки */
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(Resources.resourceAsString("sql/users/user_SELECT_credentials.sql"))
                .authoritiesByUsernameQuery(Resources.resourceAsString("sql/users/user_SELECT_roles.sql"));
    }
}
