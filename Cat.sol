pragma solidity ^0.4.24;
contract CatController {

    //CEO地址
    address ceoAddress;
    //暂停所有交易
    bool pause ;
    //是否可以发行
    bool issue ;
    function CatController(){
        ceoAddress = msg.sender;
        pause = false;
        issue = true;
    }

    //查看CEO地址
    function getCeoAddress()public constant returns(address){
        return ceoAddress;
    }

    //设置新的CEO
    function setCeoAddress(address newCeo) public returns(bool){
        require(msg.sender ==ceoAddress);
        ceoAddress = newCeo;
        return true;
    }

    //暂停猫的交易
    function setPause(bool isPause) public returns(bool){
        require(msg.sender==ceoAddress);
        pause = isPause;
        return true;
    }

    //查询猫是否暂停交易了
    function getPause() public constant returns(bool){ return pause;}

    //设置可否发行
    function setIssue(bool issue) public returns(bool){
        require(msg.sender==ceoAddress);
        CatController.issue = issue;
        return true;
    }
    //是否发行
    function getIssue() public constant returns(bool){ return CatController.issue;}

    //Cat猫
    struct CatKitty {
        //代表猫的遗传密码。 这是决定猫的长相的核心数据。
        string genes;
        //猫出生时的时间戳
        uint64 birthTime;
        //再次繁殖的区块号
        uint64 cooldownEndBlock;
        //母亲的ID
        uint32 matronId;
        //父亲的ID
        uint32 sireId;
        //如果猫当前怀孕，则设置交配对象ID
        uint32 siringWithId;
        //目前这只猫的冷却时间（猫需要等待多久才能繁殖）
        uint16 cooldownIndex;
        //这只猫的“世代号”。 第一只猫被合约创造是0代，新一代的猫是他们的父母一代中较大的一个，再加上1.
        uint16 generation;
    }

    //猫的数组
    CatKitty[] catKittys;

    //存储猫的所有权
    mapping (uint256 => address) private belonged;
    //账户所拥有的猫数量
    mapping (address => uint256) private catNumber;

    //发行 Cat
    function issueCat(
        string _genes
    )  public returns(uint256){
        CatKitty memory catKitty = CatKitty({
            genes: _genes,
            birthTime: uint64(now),
            cooldownEndBlock: uint64(0),
            matronId: uint32(0),
            sireId: uint32(0),
            siringWithId: uint32(0),
            cooldownIndex: uint16(0),
            generation: uint16(0)
            });
        uint256 catID =  catKittys.push(catKitty)-1;
        belonged[catID] = msg.sender;
        catNumber[msg.sender]++;
        return catID;
    }

    //获取猫的信息
    function catInfo(uint256 catId) public constant
    returns(
        uint256 _catId,
        string genes,
        uint64 birthTime,
        uint64 cooldownEndBlock,
        uint32 matronId,
        uint32 sireId,
        uint32 siringWithId,
        uint16 cooldownIndex,
        uint16 generation
    ){
        require(catId<catKittys.length);
        CatKitty cat =  catKittys[catId];
        return (catId,cat.genes,cat.birthTime,cat.cooldownEndBlock,cat.matronId,cat.sireId,cat.siringWithId,cat.cooldownIndex,cat.generation);
    }

    //拥有猫的数量
    function haveCatNumber() public constant returns(uint256){
        return catNumber[msg.sender];
    }

    //转让猫咪
    function transferCat(address to,uint256 catId) public returns(bool){
        require(belonged[catId] == msg.sender);
        belonged[catId] = to;
        catNumber[msg.sender]--;
        catNumber[to]++;
        return true;
    }

    //繁育新的猫咪
    function breedNewCat(uint32 matronId,uint32 sireId,string _genes) public returns(uint256){
        uint16 gener = (catKittys[matronId].generation + catKittys[sireId].generation)/2 +1;
        CatKitty memory catKitty = CatKitty({
            genes: _genes,
            birthTime: uint64(now),
            cooldownEndBlock: uint64(0),
            matronId: matronId,
            sireId: sireId,
            siringWithId: uint32(0),
            cooldownIndex: uint16(0),
            generation: gener
            });
        uint256 catID =  catKittys.push(catKitty)-1;
        belonged[catID] = msg.sender;
        catNumber[msg.sender]++;
        return catID;
    }
    // 用户拥有的所有的猫
    function findUserCat(uint256 index) public constant
    returns(
        uint256 _catId,
        string genes,
        uint64 birthTime,
        uint64 cooldownEndBlock,
        uint32 matronId,
        uint32 sireId,
        uint32 siringWithId,
        uint16 cooldownIndex,
        uint16 generation
    )
    {
        if(index >= catKittys.length){
            index = 0;
        }
        CatKitty cat ;
        uint256 catId;
        for(uint256 i=index;i<catKittys.length;i++){
            if(belonged[i]==msg.sender){
                cat = catKittys[i];
                catId = i;
                break;
            }
        }

        return (catId,cat.genes,cat.birthTime,cat.cooldownEndBlock,cat.matronId,cat.sireId,cat.siringWithId,cat.cooldownIndex,cat.generation);
    }

}