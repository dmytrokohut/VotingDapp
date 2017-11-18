package com.dkohut.voting.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
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
 * <p>Generated with web3j version 3.1.1.
 */
public final class Voting extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b6040516104f73803806104f783398101604052808051909101905060005b81518160ff1610156100be576001805480820161004a83826100c5565b91600052602060002090016000848460ff168151811061006657fe5b9060200190602002015190915550600090506002818460ff85168151811061008a57fe5b9060200190602002015181526020810191909152604001600020805460ff191660ff9290921691909117905560010161002d565b505061010f565b8154818355818115116100e9576000838152602090206100e99181019083016100ee565b505050565b61010c91905b8082111561010857600081556001016100f4565b5090565b90565b6103d98061011e6000396000f3006060604052600436106100615763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306a49fce8114610066578063230d6ed8146100cc5780633bfe7adc146100e4578063cc9ab26714610110575b600080fd5b341561007157600080fd5b610079610126565b60405160208082528190810183818151815260200191508051906020019060200280838360005b838110156100b85780820151838201526020016100a0565b505050509050019250505060405180910390f35b34156100d757600080fd5b6100e2600435610186565b005b34156100ef57600080fd5b6100fa6004356101bf565b60405160ff909116815260200160405180910390f35b341561011b57600080fd5b6100e26004356101ee565b61012e610354565b600180548060200260200160405190810160405280929190818152602001828054801561017b57602002820191906000526020600020905b81548152600190910190602001808311610166575b505050505090505b90565b600180548082016101978382610366565b506000918252602080832091909101839055918152600290915260409020805460ff19169055565b60006101ca8261028e565b15156001146101d857600080fd5b5060009081526002602052604090205460ff1690565b6101f7336102e1565b1561020157600080fd5b61020a8161028e565b151560011461021857600080fd5b6000818152600260205260408120805460ff198116600160ff928316810190921617909155815490810161024c8382610366565b506000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff19163373ffffffffffffffffffffffffffffffffffffffff1617905550565b6000805b60015460ff821610156102d6576001805484919060ff84169081106102b357fe5b60009182526020909120015414156102ce57600191506102db565b600101610292565b600091505b50919050565b6000805b60005460ff821610156102d6578273ffffffffffffffffffffffffffffffffffffffff1660008260ff1681548110151561031b57fe5b60009182526020909120015473ffffffffffffffffffffffffffffffffffffffff16141561034c57600191506102db565b6001016102e5565b60206040519081016040526000815290565b81548183558181151161038a5760008381526020902061038a91810190830161038f565b505050565b61018391905b808211156103a95760008155600101610395565b50905600a165627a7a723058208f4678d60bbf9b697f03ad4bfcf3b61c641fb3fd72d021092fb23c4e25880acb0029";

    private Voting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<DynamicArray<Bytes32>> getCandidates() {
        Function function = new Function("getCandidates", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> addCandidate(Bytes32 candidate) {
        Function function = new Function(
                "addCandidate", 
                Arrays.<Type>asList(candidate), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint8> votesOfCandidate(Bytes32 candidate) {
        Function function = new Function("votesOfCandidate", 
                Arrays.<Type>asList(candidate), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> voteForCandidate(Bytes32 candidate) {
        Function function = new Function(
                "voteForCandidate", 
                Arrays.<Type>asList(candidate), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, DynamicArray<Bytes32> candidateNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(candidateNames));
        return deployRemoteCall(Voting.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, DynamicArray<Bytes32> candidateNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(candidateNames));
        return deployRemoteCall(Voting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
