package com.peak.aortic.core.blood;

import com.peak.aortic.api.Blood;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class PigBlood extends Blood {
    public PigBlood() {
        super(EntityType.PIG);
    }

    public void passive(World world, PlayerEntity source) {
      //  source.sendMessage(Text.of("bleh!"), true);
    }
}
