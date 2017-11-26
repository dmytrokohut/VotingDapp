pragma solidity ^0.4.18;

contract Owned {
    
    address internal owner;
    
    function Owned() public {
        owner = msg.sender;
    }
    
    modifier ownerOnly() {
        require(msg.sender == owner);
        _;
    }
}

contract Mortal is Owned {
    function kill() ownerOnly external {
        selfdestruct(owner);
    }
}

contract Voting is Mortal {
    
    address[] public voterList;
    bytes32[] public candidateList;
    mapping(bytes32 => uint8) public votesReceived;
    
    event Initialized();
    event NewCandidate(address sender, bytes32 candidateName);
    
    function Voting() Owned public {}
    
    function init(bytes32[] candidateNames) ownerOnly external {
        for(uint8 i=0; i<candidateNames.length; i++) {
            candidateList.push(candidateNames[i]);
            votesReceived[candidateNames[i]] = 0;
        }
        Initialized();
    }
    
    function addCandidate(bytes32 candidate) external {
        candidateList.push(candidate);
        votesReceived[candidate] = 0;
        NewCandidate(msg.sender, candidate);
    }
    
    function voteForCandidate(bytes32 candidate) external {
        require(validVoter(msg.sender) == false);
        require(validCandidate(candidate) == true);
        
        votesReceived[candidate] += 1;
        voterList.push(msg.sender);
    }
    
    function getCandidateList() external constant returns(bytes32[]) {
        return candidateList;
    }
    
    
    function validCandidate(bytes32 candidate) private constant returns (bool) {
        for(uint8 i=0; i<candidateList.length; i++)
            if(candidateList[i] == candidate)
                return true;
                
        return false;
    }
    
    function validVoter(address sender) private constant returns (bool) {
        for(uint8 i=0; i<voterList.length; i++)
            if(voterList[i] == sender)
                return true;
        
        return false;
    }
    
}