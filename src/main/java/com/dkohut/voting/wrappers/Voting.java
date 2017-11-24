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
    private static final String BINARY = "6060604052341561000f57600080fd5b60008054600160a060020a033316600160a060020a03199091161790556105ba8061003b6000396000f30060606040526004361061008d5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663230d6ed881146100925780633368e968146100aa57806341c0e1b5146100c857806350e2e8af146100db5780637021939f1461010d578063b13c744b14610139578063cc9ab26714610161578063fdbc400614610177575b600080fd5b341561009d57600080fd5b6100a86004356101dd565b005b34156100b557600080fd5b6100a8600480356024810191013561025e565b34156100d357600080fd5b6100a8610316565b34156100e657600080fd5b6100f160043561033f565b604051600160a060020a03909116815260200160405180910390f35b341561011857600080fd5b610123600435610367565b60405160ff909116815260200160405180910390f35b341561014457600080fd5b61014f60043561037c565b60405190815260200160405180910390f35b341561016c57600080fd5b6100a860043561039b565b341561018257600080fd5b61018a61042e565b60405160208082528190810183818151815260200191508051906020019060200280838360005b838110156101c95780820151838201526020016101b1565b505050509050019250505060405180910390f35b60028054600181016101ef838261053a565b50600091825260208083209190910183905582825260039052604090819020805460ff191690557fd5b232e8b0b3cae5597d617fedb01eab27bcfa4a0f7491761e81dda85fbaae8f903390839051600160a060020a03909216825260208201526040908101905180910390a150565b6000805433600160a060020a0390811691161461027a57600080fd5b5060005b60ff81168290101561031157600280546001810161029c838261053a565b9160005260206000209001600085858560ff1681811015156102ba57fe5b602002919091013590925550600091506003905081858560ff86168181106102de57fe5b60209081029290920135835250810191909152604001600020805460ff191660ff9290921691909117905560010161027e565b505050565b60005433600160a060020a0390811691161461033157600080fd5b600054600160a060020a0316ff5b600180548290811061034d57fe5b600091825260209091200154600160a060020a0316905081565b60036020526000908152604090205460ff1681565b600280548290811061038a57fe5b600091825260209091200154905081565b6103a43361048e565b156103ae57600080fd5b6103b7816104f2565b15156001146103c557600080fd5b6000818152600360205260409020805460ff198116600160ff92831681019092161790915580548082016103f9838261053a565b506000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff191633600160a060020a031617905550565b61043661055e565b600280548060200260200160405190810160405280929190818152602001828054801561048357602002820191906000526020600020905b8154815260019091019060200180831161046e575b505050505090505b90565b6000805b60015460ff821610156104e75782600160a060020a031660018260ff168154811015156104bb57fe5b600091825260209091200154600160a060020a031614156104df57600191506104ec565b600101610492565b600091505b50919050565b6000805b60025460ff821610156104e7576002805484919060ff841690811061051757fe5b600091825260209091200154141561053257600191506104ec565b6001016104f6565b81548183558181151161031157600083815260209020610311918101908301610570565b60206040519081016040526000815290565b61048b91905b8082111561058a5760008155600101610576565b50905600a165627a7a723058201ac43757068530e19f45465a8dccdb8b694d0aa02971dff0e221b3f431877f690029";

    private Voting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
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

    public List<DestroyedEventResponse> getDestroyedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Destroyed", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<DestroyedEventResponse> responses = new ArrayList<DestroyedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            DestroyedEventResponse typedResponse = new DestroyedEventResponse();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<DestroyedEventResponse> destroyedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Destroyed", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, DestroyedEventResponse>() {
            @Override
            public DestroyedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                DestroyedEventResponse typedResponse = new DestroyedEventResponse();
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

    public static class NewCandidateEventResponse {
        public Address sender;

        public Bytes32 candidateName;
    }

    public static class DestroyedEventResponse {
    }
}
