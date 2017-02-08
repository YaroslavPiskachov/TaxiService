package ru.yaroslav.Security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.yaroslav.Entities.DispatcherEntity;
import ru.yaroslav.Services.interfaces.DispatcherService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yaroslav on 05.01.2017.
 */
@Repository
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DispatcherService dispatcherService;
    Log logger = LogFactory.getLog(getClass());
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("----------"+s);
        DispatcherEntity dispatcherEntity=dispatcherService.getDispatcherByFIO(s);
        if(dispatcherEntity==null){}
        GrantedAuthority auth = new GrantedAuthority() {
            private static final long serialVersionUID = 1L;
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
        set.add(auth);

        String username=dispatcherEntity.getFullName();
        String password=dispatcherEntity.getPersonalCode();
        UserDetails details=new User(username,password,set);

        return details;
    }
}
