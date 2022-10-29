package ir.largesize.OnlineShop.services.orders;

import ir.largesize.OnlineShop.entities.orders.Transactions;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.helper.uimodels.StartPaymentVM;
import ir.largesize.OnlineShop.repositories.order.TransactionsRepository;
import ir.largesize.OnlineShop.repositories.order.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionsService {
    @Autowired
    private TransactionsRepository repository;

    public Transactions getByAuthority(String authority) {
        Transactions data = repository.findByAuthority(authority);
        return data;
    }

    public Transactions getById(long id) {
        Optional<Transactions> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Transactions add(Transactions data) {
        return repository.save(data);
    }

    public Transactions add(StartPaymentVM startPaymentVM) {
        Transactions data= new Transactions();
        data.setStatus(startPaymentVM.getStatus());
        data.setAddDate(new Date());
        data.setAmount(startPaymentVM.getAmount());
        data.setCustomer(startPaymentVM.getCustomer());
        data.setDescription(startPaymentVM.getDescription());
        data.setRefId("");
        data.setInvoice(startPaymentVM.getInvoice());
        data.setAuthority(startPaymentVM.getAuthority());
        data.setPaymentType(startPaymentVM.getPaymentType());
        return repository.save(data);
    }

    public Transactions update(Transactions data) throws DataNotFoundException {
        Transactions oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }
        oldData.setRefId(data.getRefId());
        oldData.setVerifyStatus(data.getVerifyStatus());
        oldData.setTransactionVerify(data.getTransactionVerify());
        return repository.save(oldData);
    }


}


