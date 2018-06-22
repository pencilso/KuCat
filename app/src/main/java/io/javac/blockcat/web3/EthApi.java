package io.javac.blockcat.web3;

import android.os.Environment;
import android.util.Log;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple9;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.UUID;

import io.javac.blockcat.base.BaseSubscriber;
import io.javac.blockcat.entity.CatEntity;
import io.javac.blockcat.sol.CatToken;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pencilso on 2018/6/15/015.
 *
 * @author Pencilso
 */
public class EthApi {
    private final String SERVICE_IP = "https://rinkeby.infura.io/JpLIMuZVgfFzuRqKkR6U";
    private static final EthApi eth = new EthApi();

    public static EthApi instance() {
        return eth;
    }


    private Web3j web3j;

    private Credentials credentials;

    private CatToken catController;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    private Web3j getWeb3j() {
        if (web3j == null) {
            web3j = new JsonRpc2_0Web3j(new HttpService(SERVICE_IP));
        }
        return web3j;
    }

    public CatToken getCatController() {
        if (catController == null) {
            catController = CatToken.load(CatToken.contractAddress, getWeb3j(), credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        }
        return catController;
    }

    /**
     * 获取所有的钱包地址文件
     *
     * @return
     */
    public File[] getWalletFiles() {
        File[] files = getWalletFile().listFiles();
        return files;
    }


    /**
     * 获取内置SD卡路径
     *
     * @return
     */
    public String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    private File getWalletFile() {
        String innerSDCardPath = getInnerSDCardPath();
        File file = new File(innerSDCardPath + "/wallet/");
        File[] files = file.listFiles();
        if (!file.exists())
            file.mkdir();
        return file;
    }

    /**
     * 创建钱包
     *
     * @param password
     */
    public Credentials createWallet(String password) {
        try {
            String newWalletFile = WalletUtils.generateNewWalletFile(password, getWalletFile(), false);
            Credentials credentials = WalletUtils.loadCredentials(password, getWalletFile() + "/" + newWalletFile);
            return credentials;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Credentials loadCredentials(String password, File walletFile) {
        try {
            Credentials credentials = WalletUtils.loadCredentials(password, walletFile);
            return credentials;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 部署猫
     */
    public void deployCat() {
        CatToken.deploy(getWeb3j(), ceoCredentials(), ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT)
                .observable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CatToken>() {
                    @Override
                    public void onCompleted() {
                        Log.e("CatToken", "部署完毕");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("CatToken", "部署异常");
                    }

                    @Override
                    public void onNext(CatToken catToken) {
                        Log.e("CatToken", "合约地址:" + catToken.getContractAddress());
                    }
                });
    }


    /**
     * 加载CEO的权证
     *
     * @return
     */
    private Credentials ceoCredentials() {
        //这里 放智能合约拥有者的私钥  但是 程序发布的话  这串私钥  一定要去掉
        Credentials credentials = Credentials.create("*********************");
        return credentials;
    }

    /**
     * 创世猫
     *
     * @param subscriber
     */
    public void adoptCat(Subscriber<TransactionReceipt> subscriber) {
        String replace = UUID.randomUUID().toString().replace("-", "");
        getCatController().issueCat(replace)
                .observable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 查找下一个猫
     *
     * @param catId
     * @param subscriber
     */
    public void findUserCat(final int catId, final Subscriber<CatEntity> subscriber) {
        getCatController().findUserCat(BigInteger.valueOf(catId))
                .observable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    private CatEntity catEntity;

                    @Override
                    public void onCompleted() {
                        subscriber.onNext(catEntity);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> tuple9) {
                        catEntity = new CatEntity();
                        catEntity.setId(tuple9.getValue1());
                        catEntity.setGenes(tuple9.getValue2());
                        catEntity.setBirthTime(tuple9.getValue3());
                        catEntity.setCooldownEndBlock(tuple9.getValue4());
                        catEntity.setMatronId(tuple9.getValue5());
                        catEntity.setSireId(tuple9.getValue6());
                        catEntity.setSiringWithId(tuple9.getValue7());
                        catEntity.setCooldownIndex(tuple9.getValue8());
                        catEntity.setGeneration(tuple9.getValue9());
                    }
                });
    }

    /**
     * 转让
     *
     * @param address
     * @param catId
     * @param baseSubscriber
     */
    public void transferCat(String address, Long catId, BaseSubscriber baseSubscriber) {
        getCatController().transferCat(address, BigInteger.valueOf(catId))
                .observable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseSubscriber);
    }

    public void catMating(String matronId, String sireId, Subscriber<? super TransactionReceipt> subscribe) {
        getCatController().breedNewCat(
                BigInteger.valueOf(Long.valueOf(matronId)),
                BigInteger.valueOf(Long.valueOf(sireId)),
                UUID.randomUUID().toString().replace("-", "")
        )
                .observable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
    }
}
