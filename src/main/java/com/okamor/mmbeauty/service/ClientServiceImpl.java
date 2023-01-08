package com.okamor.mmbeauty.service;

import com.okamor.mmbeauty.model.Client;
import com.okamor.mmbeauty.model.Email;
import com.okamor.mmbeauty.model.enums.UserRole;
import com.okamor.mmbeauty.model.enums.UserStatus;
import com.okamor.mmbeauty.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Value("${password.length}")
    private int passwordLength;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmailService emailService;

    public static String passwordGenetator(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789()!#*_^";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int rndIndex = secureRandom.nextInt(chars.length());
            stringBuilder.append(chars.charAt(rndIndex));
        }
        return stringBuilder.toString();
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public long newClient(Client client) {
        Client clientInfo = clientRepository.getClientByEmail(client.getEmail());
        if (clientInfo == null) {
            Client newClient = client;
            newClient.setRegistered(new Date());
            newClient.setRole(UserRole.ROLE_USER);
            newClient.setStatus(UserStatus.USER_ENABLE);
            clientRepository.save(newClient);
            return clientRepository.getClientByEmail(newClient.getEmail()).getId();
        }
        return 999;
    }

    @Override
    public Client clientLogin(String email, String password) {
        Client clientInfo = clientRepository.getClientByEmail(email);
        if (clientInfo == null) {
            return null;
        }
        return clientInfo.getEmail().contentEquals(email) ? (clientInfo.getPassword().contentEquals(password) ? clientInfo : null) : null;
    }

    @Override
    public Client getClientInfo(String email) {
        return clientRepository.getClientByEmail(email);
    }

    @Override
    public Client getClientByID(Long id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public Client modifyClient(Client client) {
        client.setLastEdit(new Date());
        return clientRepository.save(client);
    }

    @Override
    public Boolean resetPassword(String email) {
        Client client = clientRepository.getClientByEmail(email);
        if (client != null) {
            String newPassword = passwordGenetator(passwordLength);
            String encodePassword = Base64.getEncoder().encodeToString(newPassword.getBytes());
            client.setPassword(encodePassword);
            client.setLastEdit(new Date());
            clientRepository.save(client);
            Email mail = new Email();
            mail.setRecipient(client.getEmail());
            mail.setSubject("Ваш оновлений пароль M&M Beauty School");
            String mailText = "Доброго дня. \n\nВам було згенерований новий пароль для входу у свій обліковий запис. \nПрохання, після успішного входу змінити його у своєму профілі\n\nВаш логін:" + client.getEmail() + "\nВаш новий пароль: " + newPassword;
            mail.setMsgBody(mailText);
            System.out.println(encodePassword);
            emailService.sendSimpleMail(mail);
            return true;
        } else
        return false;
    }

    @Override
    public void changeClientStatus(Long id, UserStatus status) {
        Client client = clientRepository.getClientById(id);
        client.setStatus(status);
        client.setLastEdit(new Date());
        clientRepository.save(client);
    }
}
