package me.woder.world;
import java.util.*;
import java.io.*;
/*
Clark Krusemark
22/11/2014 (dd/mm/yyyy)
nuvasuper
cdkrusemark@gmail.com

this is the BlockInfoManager class.
It reads in a plaintext file with all of the relevant block information(id, name, hardness, advised/required tool)

tool is either the tool's item id, or a number [-2,3]
-2 indicates we don't know what this block is
-1 indicates no tool is nessessary, or the block is unbreakable, check hardness to verify.
0 is unused
1 indicates a pick assists, but is unnessissary
2 indicates a shovel assists, but is unnessissary
3 indicates a axe assists, but is unnessissary

hardness is a value from 50-0, or -1 if unbreakable, -2 if we don't know what this block is.

The method you should be using is getInfo(id) to return a BlockInfo object.

*/
public class BlockInfoManager{
   private Map<Integer,String> BLOCK_NAME;
   private Map<Integer,Integer> HARDNESS;
   private Map<Integer,Integer> TOOL;
   private final File f = new File("BlockInfo.txt");
   //TODO: add meta values support
   
   public BlockInfoManager() throws FileNotFoundException {
      BLOCK_NAME = new HashMap<Integer,String>();//pass ID's size
      HARDNESS = new HashMap<Integer,Integer>();//pass ID's size
      TOOL = new HashMap<Integer,Integer>();//pass ID's size
      Scanner s = new Scanner(f);
      int blockCount = s.nextInt();
      
      while(s.hasNextLine()) {
         int id = s.nextInt();
         int hardness = s.nextInt();
         String blockName = s.next();
         int tool = s.nextInt();
         BLOCK_NAME.put(id,blockName);
         HARDNESS.put(id,hardness);
         TOOL.put(id,tool);
      }      
   }
   public BlockInfo getInfo(int id) {
      if(!isValidBlock(id)) {
         throw new IllegalArgumentException("Given ID not found");
      }
      
      String blockName = BLOCK_NAME.get(id);
      int hardness = HARDNESS.get(id);
      int tool = TOOL.get(id);
      return new BlockInfo(id,blockName,hardness,tool);
   }
   public boolean isValidBlock(int id) {
      return BLOCK_NAME.containsKey(id);
   }

}