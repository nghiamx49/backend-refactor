package com.example.projectbankend.Services;

import com.example.projectbankend.DTO.AccountDTO;
import com.example.projectbankend.Mapper.AccountMapper;
import com.example.projectbankend.Models.Account;
import com.example.projectbankend.Models.Role;
import com.example.projectbankend.Repository.AccountRepository;
import com.example.projectbankend.Repository.ProviderRepository;
import com.example.projectbankend.Repository.UserRepository;
import com.example.projectbankend.RequestModel.LoginRequest;
import com.example.projectbankend.RequestModel.ProviderRegister;
import com.example.projectbankend.RequestModel.UserRegister;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AuthenticateService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(s);
        Role role = account.getRole();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(role.getName()));
        return new User(account.getUsername(), account.getPassword(), grantedAuthorityList);
    }

    public AccountDTO accountDetail(String username) {
        return AccountMapper.accountDTO(accountRepository.findByUsername(username));
    }

    public boolean checkLogin(LoginRequest loginRequest) {
        Account account = accountRepository.findByUsername(loginRequest.getUsername());
        return account != null && passwordEncoder.matches(loginRequest.getPassword(), account.getPassword());
    }

    public void registerAsUser(UserRegister userRegister) throws Exception {
        try {
            accountRepository.createAccount(userRegister.getUsername()
                    , passwordEncoder.encode(userRegister.getPassword())
                    , userRegister.getAddress()
                    , userRegister.getPhone_number()
                    , userRegister.getEmail(),
                    2,
                    new Date()
            );
            Account userAccount = accountRepository.findByUsername(userRegister.getUsername());
            userRepository.createUser(false, userRegister.getFull_name()
                    , userRegister.getZipcode()
                    , userAccount.getId()
            );
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void registerAsProvider(ProviderRegister providerRegister) throws Exception {
        try {
            accountRepository.createAccount(providerRegister.getUsername()
                    , passwordEncoder.encode(providerRegister.getPassword())
                    , providerRegister.getAddress()
                    , providerRegister.getPhone_number()
                    , providerRegister.getEmail(),
                    3,
                    new Date()
            );
            Account providerAccount = accountRepository.findByUsername(providerRegister.getUsername());
            providerRepository.createProvider(
                    providerRegister.getBank_account_number(),
                    providerRegister.getOwner(),
                    providerRegister.getStore_name(),
                    providerAccount.getId()
            );
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

}
