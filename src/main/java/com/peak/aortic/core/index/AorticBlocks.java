package com.peak.aortic.core.index;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.block.HematologyTableBlock;
import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

/**
 * @author Chemthunder
 */
public interface AorticBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(Aortic.MOD_ID);

    Block HEMATOLOGY_TABLE = BLOCKS.registerWithItem("hematology_table", HematologyTableBlock::new, AbstractBlock.Settings.copy(Blocks.SMITHING_TABLE)
            .sounds(BlockSoundGroup.BONE)
    );

    static void init() {}
}
