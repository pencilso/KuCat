package io.javac.blockcat.entity;

import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

/**
 * Created by Pencilso on 2018/6/21/021.
 *
 * @author Pencilso
 */
public class CatEntity {
    private BigInteger id;
    /**
     * 代表猫的遗传密码。 这是决定猫的长相的核心数据。
     */
    private String genes;
    /**
     * 猫出生时的时间戳
     */
    private BigInteger birthTime;

    /**
     * 再次繁殖的区块号
     */
    private BigInteger cooldownEndBlock;
    /**
     * 母亲的ID
     */
    private BigInteger matronId;
    /**
     * 父亲的ID
     */
    private BigInteger sireId;
    /**
     * 如果猫当前怀孕，则设置交配对象ID
     */
    private BigInteger siringWithId;

    /**
     * 目前这只猫的冷却时间（猫需要等待多久才能繁殖）
     */
    private BigInteger cooldownIndex;
    /**
     * 这只猫的“世代号”。 第一只猫被合约创造是0代，新一代的猫是他们的父母一代中较大的一个，再加上1.
     */
    private BigInteger generation;


    @Override
    public String toString() {
        return "CatEntity{" +
                "id=" + id +
                ", genes='" + genes + '\'' +
                ", birthTime=" + birthTime +
                ", cooldownEndBlock=" + cooldownEndBlock +
                ", matronId=" + matronId +
                ", sireId=" + sireId +
                ", siringWithId=" + siringWithId +
                ", cooldownIndex=" + cooldownIndex +
                ", generation=" + generation +
                '}';
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getGenes() {
        return genes;
    }

    public void setGenes(String genes) {
        this.genes = genes;
    }

    public BigInteger getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(BigInteger birthTime) {
        this.birthTime = birthTime;
    }

    public BigInteger getCooldownEndBlock() {
        return cooldownEndBlock;
    }

    public void setCooldownEndBlock(BigInteger cooldownEndBlock) {
        this.cooldownEndBlock = cooldownEndBlock;
    }

    public BigInteger getMatronId() {
        return matronId;
    }

    public void setMatronId(BigInteger matronId) {
        this.matronId = matronId;
    }

    public BigInteger getSireId() {
        return sireId;
    }

    public void setSireId(BigInteger sireId) {
        this.sireId = sireId;
    }

    public BigInteger getSiringWithId() {
        return siringWithId;
    }

    public void setSiringWithId(BigInteger siringWithId) {
        this.siringWithId = siringWithId;
    }

    public BigInteger getCooldownIndex() {
        return cooldownIndex;
    }

    public void setCooldownIndex(BigInteger cooldownIndex) {
        this.cooldownIndex = cooldownIndex;
    }

    public BigInteger getGeneration() {
        return generation;
    }

    public void setGeneration(BigInteger generation) {
        this.generation = generation;
    }
}
