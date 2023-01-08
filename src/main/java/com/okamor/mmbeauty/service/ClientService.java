package com.okamor.mmbeauty.service;

import com.okamor.mmbeauty.model.Client;
import com.okamor.mmbeauty.model.enums.UserStatus;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();

    long newClient(Client client);

    Client clientLogin(String email, String password);

    Client getClientInfo(String email);

    Client getClientByID(Long id);

    Client modifyClient(Client client);

    Boolean resetPassword(String email);

    void changeClientStatus(Long id, UserStatus status);

}
