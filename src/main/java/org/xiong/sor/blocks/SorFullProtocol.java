package org.xiong.sor.blocks;

import java.util.LinkedList;
import java.util.List;


public class SorFullProtocol {
    private MapBlock mapBlock;
    private GeneralParametersBlock gpBlock;
    private SupplierParametersBlock spBlock;
    private FixedParametersBlock fpBlock;
    private KeyEventsBlock keBlock;
    private PtsBlock ptsBlock;

    public MapBlock getMapBlock() {
        return mapBlock;
    }

    public void setMapBlock(MapBlock mapBlock) {
        this.mapBlock = mapBlock;
    }

    public GeneralParametersBlock getGpBlock() {
        return gpBlock;
    }

    public void setGpBlock(GeneralParametersBlock gpBlock) {
        this.gpBlock = gpBlock;
    }

    public SupplierParametersBlock getSpBlock() {
        return spBlock;
    }

    public void setSpBlock(SupplierParametersBlock spBlock) {

        this.spBlock = spBlock;
    }

    public FixedParametersBlock getFpBlock() {
        return fpBlock;
    }

    public void setFpBlock(FixedParametersBlock fpBlock) {
        this.fpBlock = fpBlock;
    }

    public KeyEventsBlock getKeBlock() {
        return keBlock;
    }

    public void setKeBlock(KeyEventsBlock keBlock) {
        this.keBlock = keBlock;
    }

    public PtsBlock getPtsBlock() {
        return ptsBlock;
    }

    public void setPtsBlock(PtsBlock ptsBlock) {
        this.ptsBlock = ptsBlock;
    }

    public byte[] toBytes(){
        int totalSize = 0;
        MapBlock mapBlock = this.getMapBlock();

        List<MapBlock.BlockMeta> metaInfo = new LinkedList<>();
        if(gpBlock != null) {
            totalSize += gpBlock.toBytes().length;
            metaInfo.add(new MapBlock.BlockMeta(gpBlock.getPdId(), 0,gpBlock.toBytes().length));

        }
        if(spBlock != null) {
            totalSize += spBlock.toBytes().length;
            metaInfo.add(new MapBlock.BlockMeta(spBlock.getMfId(), 0, spBlock.toBytes().length));

        }
        if(fpBlock != null) {
            totalSize += fpBlock.toBytes().length;
            metaInfo.add(new MapBlock.BlockMeta(fpBlock.getFpid(), 0, fpBlock.toBytes().length));

        }
        if(keBlock != null) {
            totalSize += keBlock.toBytes().length;
            metaInfo.add(new MapBlock.BlockMeta(keBlock.getKeId(), 0, keBlock.toBytes().length));
  
        }

        if(ptsBlock != null) {
            totalSize += ptsBlock.toBytes().length;
            metaInfo.add(new MapBlock.BlockMeta(ptsBlock.getDpid(), 0, ptsBlock.toBytes().length));
        }

        if (mapBlock != null) {
            mapBlock.setMetaInfo(metaInfo.toArray(new MapBlock.BlockMeta[0]));
            mapBlock.setNb((short)(metaInfo.size()+1));
            totalSize += mapBlock.toBytes().length;
        }

        byte[] result = new byte[totalSize];
        int pos = 0;

        if (mapBlock != null) {
            byte[] mbBytes = mapBlock.toBytes();
            System.arraycopy(mbBytes, 0, result, pos, mbBytes.length);
            pos += mbBytes.length;
        }
        if (gpBlock != null) {
            byte[] gpBytes = gpBlock.toBytes();
            System.arraycopy(gpBytes, 0, result, pos, gpBytes.length);
            pos += gpBytes.length;
        }
        if (spBlock != null) {
            byte[] spBytes = spBlock.toBytes();
            System.arraycopy(spBytes, 0, result, pos, spBytes.length);
            pos += spBytes.length;
        }
        if (fpBlock != null) {
            byte[] fpBytes = fpBlock.toBytes();
            System.arraycopy(fpBytes, 0, result, pos, fpBytes.length);
            pos += fpBytes.length;
        }
        if (keBlock != null) {
            byte[] keBytes = keBlock.toBytes();
            System.arraycopy(keBytes, 0, result, pos, keBytes.length);
            pos += keBytes.length;
        }
        if (ptsBlock != null) {
            byte[] ptsBytes = ptsBlock.toBytes();
            System.arraycopy(ptsBytes, 0, result, pos, ptsBytes.length);
        }

        return result;
    }

    @Override
    public String toString() {
        return "SorFullProtocol [mapBlock=" + mapBlock + ", gpBlock=" + gpBlock + ", spBlock=" + spBlock + ", fpBlock="
                + fpBlock + ", keBlock=" + keBlock + ", ptsBlock=" + ptsBlock + "]";
    }

    
}
