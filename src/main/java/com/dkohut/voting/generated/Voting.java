package com.dkohut.voting.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.0.1.
 */
public final class Voting extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b60405161041738038061041783398101604052808051909101905060005b81518160ff1610156100be576001805480820161004a83826100c5565b91600052602060002090016000848460ff168151811061006657fe5b9060200190602002015190915550600090506002818460ff85168151811061008a57fe5b9060200190602002015181526020810191909152604001600020805460ff191660ff9290921691909117905560010161002d565b505061010f565b8154818355818115116100e9576000838152602090206100e99181019083016100ee565b505050565b61010c91905b8082111561010857600081556001016100f4565b5090565b90565b6102f98061011e6000396000f3006060604052600436106100565763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663230d6ed8811461005b5780633bfe7adc14610073578063cc9ab2671461009f575b600080fd5b341561006657600080fd5b6100716004356100b5565b005b341561007e57600080fd5b6100896004356100ee565b60405160ff909116815260200160405180910390f35b34156100aa57600080fd5b61007160043561011d565b600180548082016100c68382610283565b506000918252602080832091909101839055918152600290915260409020805460ff19169055565b60006100f9826101bd565b151560011461010757600080fd5b5060009081526002602052604090205460ff1690565b61012633610210565b1561013057600080fd5b610139816101bd565b151560011461014757600080fd5b6000818152600260205260408120805460ff198116600160ff928316810190921617909155815490810161017b8382610283565b506000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff19163373ffffffffffffffffffffffffffffffffffffffff1617905550565b6000805b60015460ff82161015610205576001805484919060ff84169081106101e257fe5b60009182526020909120015414156101fd576001915061020a565b6001016101c1565b600091505b50919050565b6000805b60005460ff82161015610205578273ffffffffffffffffffffffffffffffffffffffff1660008260ff1681548110151561024a57fe5b60009182526020909120015473ffffffffffffffffffffffffffffffffffffffff16141561027b576001915061020a565b600101610214565b8154818355818115116102a7576000838152602090206102a79181019083016102ac565b505050565b6102ca91905b808211156102c657600081556001016102b2565b5090565b905600a165627a7a72305820a17f6b82166856a55deb91fbbbca657e475c94802fc12494875db86b8ff2d56d0029";

    private Voting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> addCandidate(Bytes32 candidate) {
        Function function = new Function(
                "addCandidate", 
                Arrays.<Type>asList(candidate), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> votesOfCandidate(Bytes32 candidate) {
        Function function = new Function("votesOfCandidate", 
                Arrays.<Type>asList(candidate), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> voteForCandidate(Bytes32 candidate) {
        Function function = new Function(
                "voteForCandidate", 
                Arrays.<Type>asList(candidate), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, List<Bytes32> candidateNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(
        		new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(candidateNames)));
        return deployRemoteCall(Voting.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, List<Bytes32> candidateNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(
        		new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(candidateNames)));
        return deployRemoteCall(Voting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
