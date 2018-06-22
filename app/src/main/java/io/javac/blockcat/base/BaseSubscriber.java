package io.javac.blockcat.base;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import rx.Subscriber;

/**
 * Created by Pencilso on 2018/6/20/020.
 *
 * @author Pencilso
 */
public abstract class BaseSubscriber extends Subscriber<TransactionReceipt> {
    private TransactionReceipt transactionReceipt = null;

    @Override
    public void onCompleted() {
        try {
            if (transactionReceipt != null && transactionReceipt.getStatus() != null) {

                if (!"0x0".equals(transactionReceipt.getStatus())) {
                    onCompleted(true, transactionReceipt, null);
                    return;
                }
            }
            onCompleted(false, transactionReceipt, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onCompleted(false, null, e);
    }

    @Override
    public void onNext(TransactionReceipt transactionReceipt) {
        this.transactionReceipt = transactionReceipt;
    }

    public abstract void onCompleted(boolean state, TransactionReceipt transactionReceipt, Throwable throwable);
}
