package com.dkohut.voting.wrappers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

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
    private static final String BINARY = "6060604052341561000f57600080fd5b60008054600160a060020a033316600160a060020a03199091161790556105eb8061003b6000396000f30060606040526004361061008d5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663230d6ed881146100925780633368e968146100aa57806341c0e1b5146100c857806350e2e8af146100db5780637021939f1461010d578063b13c744b14610139578063cc9ab26714610161578063fdbc400614610177575b600080fd5b341561009d57600080fd5b6100a86004356101dd565b005b34156100b557600080fd5b6100a8600480356024810191013561025e565b34156100d357600080fd5b6100a8610342565b34156100e657600080fd5b6100f160043561036b565b604051600160a060020a03909116815260200160405180910390f35b341561011857600080fd5b610123600435610393565b60405160ff909116815260200160405180910390f35b341561014457600080fd5b61014f6004356103a8565b60405190815260200160405180910390f35b341561016c57600080fd5b6100a86004356103c7565b341561018257600080fd5b61018a61045a565b60405160208082528190810183818151815260200191508051906020019060200280838360005b838110156101c95780820151838201526020016101b1565b505050509050019250505060405180910390f35b60028054600181016101ef8382610566565b50600091825260208083209190910183905582825260039052604090819020805460ff191690557fd5b232e8b0b3cae5597d617fedb01eab27bcfa4a0f7491761e81dda85fbaae8f903390839051600160a060020a03909216825260208201526040908101905180910390a150565b6000805433600160a060020a0390811691161461027a57600080fd5b5060005b60ff81168290101561031157600280546001810161029c8382610566565b9160005260206000209001600085858560ff1681811015156102ba57fe5b602002919091013590925550600091506003905081858560ff86168181106102de57fe5b60209081029290920135835250810191909152604001600020805460ff191660ff9290921691909117905560010161027e565b7f5daa87a0e9463431830481fd4b6e3403442dfb9a12b9c07597e9f61d50b633c860405160405180910390a1505050565b60005433600160a060020a0390811691161461035d57600080fd5b600054600160a060020a0316ff5b600180548290811061037957fe5b600091825260209091200154600160a060020a0316905081565b60036020526000908152604090205460ff1681565b60028054829081106103b657fe5b600091825260209091200154905081565b6103d0336104ba565b156103da57600080fd5b6103e38161051e565b15156001146103f157600080fd5b6000818152600360205260409020805460ff198116600160ff92831681019092161790915580548082016104258382610566565b506000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff191633600160a060020a031617905550565b61046261058f565b60028054806020026020016040519081016040528092919081815260200182805480156104af57602002820191906000526020600020905b8154815260019091019060200180831161049a575b505050505090505b90565b6000805b60015460ff821610156105135782600160a060020a031660018260ff168154811015156104e757fe5b600091825260209091200154600160a060020a0316141561050b5760019150610518565b6001016104be565b600091505b50919050565b6000805b60025460ff82161015610513576002805484919060ff841690811061054357fe5b600091825260209091200154141561055e5760019150610518565b600101610522565b81548183558181151161058a5760008381526020902061058a9181019083016105a1565b505050565b60206040519081016040526000815290565b6104b791905b808211156105bb57600081556001016105a7565b50905600a165627a7a7230582008e4342a387c72bfceb2076442585560466bc1fbaa7eb2c6c08dd9df27a68de30029";

    private Voting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<InitializedEventResponse> getInitializedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Initialized", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<InitializedEventResponse> responses = new ArrayList<InitializedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            InitializedEventResponse typedResponse = new InitializedEventResponse();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<InitializedEventResponse> initializedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Initialized", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, InitializedEventResponse>() {
            @Override
            public InitializedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                InitializedEventResponse typedResponse = new InitializedEventResponse();
                return typedResponse;
            }
        });
    }

    public List<NewCandidateEventResponse> getNewCandidateEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("NewCandidate", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<NewCandidateEventResponse> responses = new ArrayList<NewCandidateEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            NewCandidateEventResponse typedResponse = new NewCandidateEventResponse();
            typedResponse.sender = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.candidateName = (Bytes32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewCandidateEventResponse> newCandidateEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("NewCandidate", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewCandidateEventResponse>() {
            @Override
            public NewCandidateEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                NewCandidateEventResponse typedResponse = new NewCandidateEventResponse();
                typedResponse.sender = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.candidateName = (Bytes32) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> addCandidate(Bytes32 candidate) {
        Function function = new Function(
                "addCandidate", 
                Arrays.<Type>asList(candidate), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> init(DynamicArray<Bytes32> candidateNames) {
        Function function = new Function(
                "init", 
                Arrays.<Type>asList(candidateNames), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> kill() {
        Function function = new Function(
                "kill", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> voterList(Uint256 param0) {
        Function function = new Function("voterList", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint8> votesReceived(Bytes32 param0) {
        Function function = new Function("votesReceived", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bytes32> candidateList(Uint256 param0) {
        Function function = new Function("candidateList", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> voteForCandidate(Bytes32 candidate) {
        Function function = new Function(
                "voteForCandidate", 
                Arrays.<Type>asList(candidate), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<DynamicArray<Bytes32>> getCandidateList() {
        Function function = new Function("getCandidateList", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Voting.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Voting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class InitializedEventResponse {
    }

    public static class NewCandidateEventResponse {
        public Address sender;

        public Bytes32 candidateName;
    }
}
