package org.xiong;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.xiong.sor.GenBlockMethods;
import org.xiong.sor.blocks.GeneralParametersBlock;
import org.xiong.sor.blocks.KeyEventsBlock;
import org.xiong.sor.blocks.MapBlock;
import org.xiong.sor.blocks.PtsBlock;
import org.xiong.sor.blocks.SorFullProtocol;
import org.xiong.sor.blocks.SupplierParametersBlock;


public class Main {

    public static void main(String[] args) {
        GenBlockMethods<SorSample> genBlockMethods = new GenBlockMethods<SorSample>() {
            @Override
            public SorFullProtocol convertSorProtocol(SorSample origin) {
                SorFullProtocol sorFullProtocol = new SorFullProtocol();
                MapBlock mapBlock = new MapBlock();
                GeneralParametersBlock generalParametersBlock = new GeneralParametersBlock();
                KeyEventsBlock keyEventsBlock = new KeyEventsBlock();
                KeyEventsBlock.Event event= new KeyEventsBlock.Event();
                event.setCmt(origin.getEventComment());
                keyEventsBlock.addEvent(event);
                SupplierParametersBlock supplierParametersBlock = new SupplierParametersBlock();
                PtsBlock ptsBlock = new PtsBlock();
                ptsBlock.setSf(origin.getCurve());
                

                sorFullProtocol.setMapBlock(mapBlock);
                sorFullProtocol.setGpBlock(generalParametersBlock);
                sorFullProtocol.setKeBlock(keyEventsBlock);
                sorFullProtocol.setSpBlock(supplierParametersBlock);
                sorFullProtocol.setPtsBlock(ptsBlock);
                
                // convert data to blocks

                return sorFullProtocol;
            }
        };
        SorSample sample = new SorSample(new int[]{1, 2, 3}, "Sample Event");
        SorFullProtocol protocol = genBlockMethods.convertSorProtocol(sample);
        System.out.println("Converted protocol: " + protocol.toString());
        byte[] protocolBytes = GenBlockMethods.getProtocolBytes(protocol);
        System.out.println("bytes length: " + protocolBytes.length);
        System.out.println("bytes: " + Arrays.toString(protocolBytes));

        // 写入 .sor 文件
        String timeMillisString= String.valueOf(System.currentTimeMillis());
        String outputFileName = "output_" + timeMillisString + ".sor";
        try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.write(protocolBytes);
            System.out.println("Successfully wrote " + protocolBytes.length + " bytes to " + outputFileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }

    }  
    
}
