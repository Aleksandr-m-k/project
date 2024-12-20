package com.bank.transfer.serviceImpl;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import com.bank.transfer.service.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {
    private final AccountTransferRepository accountTransferRepository;

    @Autowired
    public AccountTransferServiceImpl(AccountTransferRepository accountTransferRepository) {
        this.accountTransferRepository = accountTransferRepository;
    }

    @Override
    @Transactional
    public Optional<AccountTransfer> getAccountTransferById(Long id) {
        return accountTransferRepository.findById(id);
    }

    @Override
    @Transactional
    public AccountTransfer findTransferByAccountNumber(Long accountNumber) {
        return accountTransferRepository.findByAccountNumber(accountNumber);
    }

    @Override
    @Transactional
    public List<AccountTransfer> allAccountTransfer() {
        return accountTransferRepository.findAll();
    }


    @Override
    @Transactional
    public AccountTransfer saveAccountTransfer(AccountTransfer accountTransfer) {
        return accountTransferRepository.save(accountTransfer);
    }


    @Override
    @Transactional
    public AccountTransfer updateAccountTransferById(AccountTransfer accountTransferToUpdate, long id) {
        if (accountTransferToUpdate == null) {
            throw new IllegalArgumentException("AccountTransfer to update cannot be null");
        }

        final Optional<AccountTransfer> optionalAccountTransfer = getAccountTransferById(id);

        final AccountTransfer accountTransfer = optionalAccountTransfer.orElseThrow(() ->
                new EntityNotFoundException("AccountTransfer not found for id: " + id));


        accountTransfer.setAccountNumber(accountTransferToUpdate.getAccountNumber());
        accountTransfer.setAmount(accountTransferToUpdate.getAmount());
        accountTransfer.setPurpose(accountTransferToUpdate.getPurpose());
        accountTransfer.setAccountDetailsId(accountTransferToUpdate.getAccountDetailsId());

        return accountTransfer;
    }

    @Override
    @Transactional
    public void deleteAccountTransfer(Long id) {
        accountTransferRepository.deleteById(id);
    }
}
