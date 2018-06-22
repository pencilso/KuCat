package io.javac.blockcat.sol;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple9;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class CatToken extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008060146101000a81548160ff0219169083151502179055506001600060156101000a81548160ff0219169083151502179055506114d7806100956000396000f3006080604052600436106100ba576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063042956ec146100bf57806325d630c8146100ea57806328ddef50146101455780633e3ca9d314610174578063407d6b8d146101a35780634489a00c146101ea57806348f98b171461026757806366b0d80d146102cc578063bedb86fb14610406578063c04961bf1461044d578063e431f3c614610587578063ebe4a865146105de575b600080fd5b3480156100cb57600080fd5b506100d461067b565b6040518082815260200191505060405180910390f35b3480156100f657600080fd5b5061012b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506106c2565b604051808215151515815260200191505060405180910390f35b34801561015157600080fd5b5061015a610768565b604051808215151515815260200191505060405180910390f35b34801561018057600080fd5b5061018961077e565b604051808215151515815260200191505060405180910390f35b3480156101af57600080fd5b506101d0600480360381019080803515159060200190929190505050610794565b604051808215151515815260200191505060405180910390f35b3480156101f657600080fd5b50610251600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610814565b6040518082815260200191505060405180910390f35b34801561027357600080fd5b506102b2600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610aa6565b604051808215151515815260200191505060405180910390f35b3480156102d857600080fd5b506102f760048036038101908080359060200190929190505050610c10565b604051808a8152602001806020018967ffffffffffffffff1667ffffffffffffffff1681526020018867ffffffffffffffff1667ffffffffffffffff1681526020018763ffffffff1663ffffffff1681526020018663ffffffff1663ffffffff1681526020018563ffffffff1663ffffffff1681526020018461ffff1661ffff1681526020018361ffff1661ffff16815260200182810382528a818151815260200191508051906020019080838360005b838110156103c35780820151818401526020810190506103a8565b50505050905090810190601f1680156103f05780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b34801561041257600080fd5b50610433600480360381019080803515159060200190929190505050610daa565b604051808215151515815260200191505060405180910390f35b34801561045957600080fd5b5061047860048036038101908080359060200190929190505050610e2a565b604051808a8152602001806020018967ffffffffffffffff1667ffffffffffffffff1681526020018867ffffffffffffffff1667ffffffffffffffff1681526020018763ffffffff1663ffffffff1681526020018663ffffffff1663ffffffff1681526020018563ffffffff1663ffffffff1681526020018461ffff1661ffff1681526020018361ffff1661ffff16815260200182810382528a818151815260200191508051906020019080838360005b83811015610544578082015181840152602081019050610529565b50505050905090810190601f1680156105715780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b34801561059357600080fd5b5061059c611056565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156105ea57600080fd5b50610665600480360381019080803563ffffffff169060200190929190803563ffffffff169060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061107f565b6040518082815260200191505060405180910390f35b6000600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561071f57600080fd5b816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b60008060159054906101000a900460ff16905090565b60008060149054906101000a900460ff16905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156107f157600080fd5b81600060156101000a81548160ff02191690831515021790555060019050919050565b600061081e611392565b6000610100604051908101604052808581526020014267ffffffffffffffff168152602001600067ffffffffffffffff168152602001600063ffffffff168152602001600063ffffffff168152602001600063ffffffff168152602001600061ffff168152602001600061ffff168152509150600180839080600181540180825580915050906001820390600052602060002090600202016000909192909190915060008201518160000190805190602001906108dc929190611406565b5060208201518160010160006101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555060408201518160010160086101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555060608201518160010160106101000a81548163ffffffff021916908363ffffffff16021790555060808201518160010160146101000a81548163ffffffff021916908363ffffffff16021790555060a08201518160010160186101000a81548163ffffffff021916908363ffffffff16021790555060c082015181600101601c6101000a81548161ffff021916908361ffff16021790555060e082015181600101601e6101000a81548161ffff021916908361ffff1602179055505050039050336002600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600081548092919060010191905055508092505050919050565b60003373ffffffffffffffffffffffffffffffffffffffff166002600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610b1557600080fd5b826002600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000815480929190600190039190505550600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600081548092919060010191905055506001905092915050565b600060606000806000806000806000806001805490508b101515610c3357600080fd5b60018b815481101515610c4257fe5b906000526020600020906002020190508a816000018260010160009054906101000a900467ffffffffffffffff168360010160089054906101000a900467ffffffffffffffff168460010160109054906101000a900463ffffffff168560010160149054906101000a900463ffffffff168660010160189054906101000a900463ffffffff1687600101601c9054906101000a900461ffff1688600101601e9054906101000a900461ffff16878054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d835780601f10610d5857610100808354040283529160200191610d83565b820191906000526020600020905b815481529060010190602001808311610d6657829003601f168201915b50505050509750995099509950995099509950995099509950509193959799909294969850565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610e0757600080fd5b81600060146101000a81548160ff02191690831515021790555060019050919050565b600060606000806000806000806000806000806001805490508d101515610e505760009c505b8c90505b600180549050811015610efc573373ffffffffffffffffffffffffffffffffffffffff166002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610eef57600181815481101515610ed757fe5b90600052602060002090600202019250809150610efc565b8080600101915050610e54565b81836000018460010160009054906101000a900467ffffffffffffffff168560010160089054906101000a900467ffffffffffffffff168660010160109054906101000a900463ffffffff168760010160149054906101000a900463ffffffff168860010160189054906101000a900463ffffffff1689600101601c9054906101000a900461ffff168a600101601e9054906101000a900461ffff16878054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561102d5780601f106110025761010080835404028352916020019161102d565b820191906000526020600020905b81548152906001019060200180831161101057829003601f168201915b505050505097509b509b509b509b509b509b509b509b509b505050509193959799909294969850565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008061108a611392565b60006001600260018863ffffffff168154811015156110a557fe5b9060005260206000209060020201600101601e9054906101000a900461ffff1660018a63ffffffff168154811015156110da57fe5b9060005260206000209060020201600101601e9054906101000a900461ffff160161ffff1681151561110857fe5b04019250610100604051908101604052808681526020014267ffffffffffffffff168152602001600067ffffffffffffffff1681526020018863ffffffff1681526020018763ffffffff168152602001600063ffffffff168152602001600061ffff1681526020018461ffff168152509150600180839080600181540180825580915050906001820390600052602060002090600202016000909192909190915060008201518160000190805190602001906111c5929190611406565b5060208201518160010160006101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555060408201518160010160086101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555060608201518160010160106101000a81548163ffffffff021916908363ffffffff16021790555060808201518160010160146101000a81548163ffffffff021916908363ffffffff16021790555060a08201518160010160186101000a81548163ffffffff021916908363ffffffff16021790555060c082015181600101601c6101000a81548161ffff021916908361ffff16021790555060e082015181600101601e6101000a81548161ffff021916908361ffff1602179055505050039050336002600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600081548092919060010191905055508093505050509392505050565b6101006040519081016040528060608152602001600067ffffffffffffffff168152602001600067ffffffffffffffff168152602001600063ffffffff168152602001600063ffffffff168152602001600063ffffffff168152602001600061ffff168152602001600061ffff1681525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061144757805160ff1916838001178555611475565b82800160010185558215611475579182015b82811115611474578251825591602001919060010190611459565b5b5090506114829190611486565b5090565b6114a891905b808211156114a457600081600090555060010161148c565b5090565b905600a165627a7a7230582049ded990d7b9ec97be8e0d17935e87b00a010bef87bd2952c17abb5a3212e29b0029";

    public static final String FUNC_HAVECATNUMBER = "haveCatNumber";

    public static final String FUNC_SETCEOADDRESS = "setCeoAddress";

    public static final String FUNC_GETISSUE = "getIssue";

    public static final String FUNC_GETPAUSE = "getPause";

    public static final String FUNC_SETISSUE = "setIssue";

    public static final String FUNC_ISSUECAT = "issueCat";

    public static final String FUNC_TRANSFERCAT = "transferCat";

    public static final String FUNC_CATINFO = "catInfo";

    public static final String FUNC_SETPAUSE = "setPause";

    public static final String FUNC_FINDUSERCAT = "findUserCat";

    public static final String FUNC_GETCEOADDRESS = "getCeoAddress";

    public static final String FUNC_BREEDNEWCAT = "breedNewCat";
    public static String contractAddress = "0x5D06F1227c0E75BF7Cc8b449323Fc0510F80B3f0";

    protected CatToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CatToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> haveCatNumber() {
        final Function function = new Function(FUNC_HAVECATNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCeoAddress(String newCeo) {
        final Function function = new Function(
                FUNC_SETCEOADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newCeo)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> getIssue() {
        final Function function = new Function(FUNC_GETISSUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> getPause() {
        final Function function = new Function(FUNC_GETPAUSE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> setIssue(Boolean issue) {
        final Function function = new Function(
                FUNC_SETISSUE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(issue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> issueCat(String _genes) {
        final Function function = new Function(
                FUNC_ISSUECAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_genes)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferCat(String to, BigInteger catId) {
        final Function function = new Function(
                FUNC_TRANSFERCAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(catId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> catInfo(BigInteger catId) {
        final Function function = new Function(FUNC_CATINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(catId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}));
        return new RemoteCall<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setPause(Boolean isPause) {
        final Function function = new Function(
                FUNC_SETPAUSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(isPause)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> findUserCat(BigInteger index) {
        final Function function = new Function(FUNC_FINDUSERCAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}));
        return new RemoteCall<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue());
                    }
                });
    }

    public RemoteCall<String> getCeoAddress() {
        final Function function = new Function(FUNC_GETCEOADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> breedNewCat(BigInteger matronId, BigInteger sireId, String _genes) {
        final Function function = new Function(
                FUNC_BREEDNEWCAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(matronId), 
                new org.web3j.abi.datatypes.generated.Uint32(sireId), 
                new org.web3j.abi.datatypes.Utf8String(_genes)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<CatToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CatToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CatToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CatToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static CatToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CatToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CatToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CatToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
